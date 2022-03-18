variable "product" {
  type    = string
  default = "rpts"
}

variable "component" {
  type    = string
  default = "api"
}

variable "location" {
  default = "UK South"
}

variable "env" {
  type = string
}

variable "subscription" {
  type    = string
}

variable "common_tags" {
  type = map(string)
}

variable "postgresql_version" {
  default = "11"
}

variable "postgresql_user" {
  type    = string
  default = "rpts"
}

variable "team_name" {
  type    = string
  default = "rpts"
}

variable "product_name" {
  type    = string
  default = "rpts"
}

variable "database_name" {
  type    = string
  default = "rpts"
}

variable "appinsights_location" {
  default     = "West Europe"
  description = "Location for Application Insights"
}
