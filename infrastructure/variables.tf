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

variable "env" {}

variable "subscription" {}

variable "deployment_namespace" {
  default = ""
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
