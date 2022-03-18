variable "product" {}

variable "component" {}

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
