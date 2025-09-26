provider "azurerm" {
  features {}
}

provider "azurerm" {
  features {}
  resource_provider_registrations = "none"
  alias                      = "postgres_network"
  subscription_id            = var.aks_subscription_id
}

terraform {
  backend "azurerm" {}

  required_providers {
    azurerm = {
      source  = "hashicorp/azurerm"
      version = "4.44.0"
    }
    azuread = {
      source  = "hashicorp/azuread"
      version = "3.6.0"
    }
  }
}

locals {
  vault_name          = "${var.product}-${var.env}"
  resource_group_name = "${var.product}-${var.env}"
  db_host_name        = "${var.product}-${var.component}-flexible-postgres-db-v15"
  db_name             = replace(var.component, "-", "")
  postgresql_user     = "${local.db_name}_user"
}

data "azurerm_key_vault" "rpts_key_vault" {
  count = contains(["aat", "demo"], var.env) ? 1 : 0
  name                = local.vault_name
  resource_group_name = local.resource_group_name
}

module "postgresql" {
  count = contains(["aat", "demo"], var.env) ? 1 : 0

  providers = {
    azurerm.postgres_network = azurerm.postgres_network
  }

  source               = "git@github.com:hmcts/terraform-module-postgresql-flexible?ref=master"
  name                 = local.db_host_name
  product              = var.product
  component            = var.component
  location             = var.location
  env                  = var.env
  pgsql_admin_username = local.postgresql_user
  pgsql_databases = [
    {
      name : var.database_name
    }
  ]
  common_tags   = var.common_tags
  business_area = "cft"
  pgsql_version = "15"

  admin_user_object_id = var.jenkins_AAD_objectId
}

resource "azurerm_resource_group" "rg" {
  count    = contains(["aat", "demo"], var.env) ? 1 : 0
  name     = "${var.product}-${var.component}-${var.env}"
  location = var.location

  tags = var.common_tags
}
