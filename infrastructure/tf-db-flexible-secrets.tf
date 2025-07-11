locals {
  flexible_secret_prefix = "${var.component}-POSTGRES-FLEXIBLE"

  flexible_secrets = contains(["aat", "demo"], var.env) ? [
    {
      name_suffix = "PASS"
      value       = module.postgresql[0].password
    },
    {
      name_suffix = "HOST"
      value       = module.postgresql[0].fqdn
    },
    {
      name_suffix = "USER"
      value       = module.postgresql[0].username
    },
    {
      name_suffix = "PORT"
      value       = "5432"
    },
    {
      name_suffix = "DATABASE"
      value       = local.db_name
    }
  ] : []
}

resource "azurerm_key_vault_secret" "flexible_secret" {
  for_each     = { for secret in local.flexible_secrets : secret.name_suffix => secret }
  key_vault_id = data.azurerm_key_vault.rpts_key_vault.id
  name         = "${local.flexible_secret_prefix}-${each.value.name_suffix}"
  value        = each.value.value
  tags = merge(var.common_tags, {
    "source" : "${var.component} PostgreSQL"
  })
  content_type    = ""
  expiration_date = timeadd(timestamp(), "17520h")
}
