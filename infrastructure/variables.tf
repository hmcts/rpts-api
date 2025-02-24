variable "product" {
  type    = string
  default = "rpts"
}

variable "location" {
  default = "UK South"
}

variable "component" {
  default = "api"
}

variable "env" {
  type = string
}

variable "subscription" {
  type = string
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

variable "aks_subscription_id" {
  default = ""
}

variable "jenkins_AAD_objectId" {}

variable "alert_location" {
  description = "Target Azure location to deploy the alert"
  type        = string
  default     = "uksouth"
}
