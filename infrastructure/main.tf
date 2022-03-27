provider "azurerm" {
  features {}
}

terraform {
  backend "azurerm" {}

  required_providers {
    azurerm = {
      source = "hashicorp/azurerm"
      version = "3.0.2"
    }
    azuread = {
      source  = "hashicorp/azuread"
      version = "2.3.0"
    }
  }
}

locals {
  vault_name = "${var.product}-${var.env}"
  resource_group_name = "${var.product}-${var.env}"
}

data "azurerm_key_vault" "rpts_key_vault" {
  name = local.vault_name
  resource_group_name = local.resource_group_name
}

// Database Infra
module "rpts-database-v11" {
  source = "git@github.com:hmcts/cnp-module-postgres?ref=postgresql_tf"
  product = var.product
  name = "${var.product}-${var.component}-postgres-db-v11"
  location = var.location
  env = var.env
  postgresql_user = var.postgresql_user
  database_name = var.database_name
  sku_name = "GP_Gen5_2"
  sku_tier = "GeneralPurpose"
  storage_mb = "51200"
  common_tags = var.common_tags
  subscription = var.subscription
  postgresql_version = var.postgresql_version
}

resource "azurerm_key_vault_secret" "POSTGRES-PASS" {
  name      = "api-POSTGRES-PASS"
  value     = module.rpts-database-v11.postgresql_password
  key_vault_id = data.azurerm_key_vault.rpts_key_vault.id
}

resource "azurerm_resource_group" "rg" {
  name     = "${var.product}-${var.component}-${var.env}"
  location = var.location

  tags = var.common_tags
}

resource "azurerm_application_insights" "appinsights" {
  name                = "${var.product}-${var.component}-appinsights-${var.env}"
  location            = var.appinsights_location
  resource_group_name = azurerm_resource_group.rg.name
  application_type    = "web"

  tags = var.common_tags

  lifecycle {
    ignore_changes = [
      # Ignore changes to appinsights as otherwise upgrading to the Azure provider 2.x
      # destroys and re-creates this appinsights instance
      application_type,
    ]
  }
}
