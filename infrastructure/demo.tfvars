moved {
  from = azurerm_application_insights.appinsights
  to   = module.application_insights[0].azurerm_application_insights.this
}
