resource "azurerm_key_vault_secret" "app_insights_connection_string" {
  count        = contains(["aat", "demo", "prod"], var.env) ? 1 : 0
  name         = "app-insights-connection-string"
  value        = module.application_insights[0].connection_string
  key_vault_id = data.azurerm_key_vault.rpts_key_vault.id
}
