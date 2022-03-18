provider "azurerm" {
  features {}
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
  source = "git@github.com:hmcts/cnp-module-postgres?ref=master"
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

# Populate Vault with DB info
resource "azurerm_key_vault_secret" "POSTGRES-USER" {
  name      = "api-POSTGRES-USER"
  value     = module.rpts-database-v11.user_name
  key_vault_id = data.azurerm_key_vault.rpts_key_vault.id
}

resource "azurerm_key_vault_secret" "POSTGRES-PASS" {
  name      = "api-POSTGRES-PASS"
  value     = module.rpts-database-v11.postgresql_password
  key_vault_id = data.azurerm_key_vault.rpts_key_vault.id
}

resource "azurerm_key_vault_secret" "POSTGRES_HOST" {
  name      = "api-POSTGRES-HOST"
  value     = module.rpts-database-v11.host_name
  key_vault_id = data.azurerm_key_vault.rpts_key_vault.id
}

resource "azurerm_key_vault_secret" "POSTGRES_PORT" {
  name      = "api-POSTGRES-PORT"
  value     = module.rpts-database-v11.postgresql_listen_port
  key_vault_id = data.azurerm_key_vault.rpts_key_vault.id
}

resource "azurerm_key_vault_secret" "POSTGRES_DATABASE" {
  name      = "api-POSTGRES-DATABASE"
  value     = module.rpts-database-v11.postgresql_database
  key_vault_id = data.azurerm_key_vault.rpts_key_vault.id
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
