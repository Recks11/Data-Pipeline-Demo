provider "confluentcloud" {
  username = var.confluentic_username
  password = var.confluentic_password
}

resource "confluentcloud_environment" "environment" {
  name = var.confluentic_env_name
}

resource "confluentcloud_kafka_cluster" "cluster" {
  name             = var.confluentic_cluster_name
  service_provider = var.confluentic_cluster_provider_name
  region           = var.confluentic_provider_region
  availability     = var.confluentic_provider_availability
  environment_id   = confluentcloud_environment.environment.id
  deployment = {
    sku = var.confluentic_deployment_sku
  }
  network_egress  = 100
  network_ingress = 100
  storage         = 5000
}

resource "confluentcloud_schema_registry" "demo" {
  environment_id   = confluentcloud_environment.environment.id
  service_provider = var.confluentic_cluster_provider_name
  region           = "EU"

  # Requires at least one kafka cluster to enable the schema registry in the environment.
  depends_on = [confluentcloud_kafka_cluster.cluster]
}

resource "confluentcloud_api_key" "api_key" {
  cluster_id     = confluentcloud_kafka_cluster.cluster.id
  environment_id = confluentcloud_environment.environment.id
}

resource "confluentcloud_service_account" "account" {
  name        = "terraform"
  description = "terraform service account"
}

