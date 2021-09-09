locals {
  bootstrap_servers = [replace(confluentcloud_kafka_cluster.cluster.bootstrap_servers, "SASL_SSL://", "")]
}

provider "kafka" {
  bootstrap_servers = local.bootstrap_servers

  tls_enabled    = true
  sasl_username  = confluentcloud_api_key.api_key.key
  sasl_password  = confluentcloud_api_key.api_key.secret
  sasl_mechanism = "plain"
  timeout        = 10
}

resource "kafka_topic" "topic-1" {
  name               = var.confluentic_kafka_ingestion_topic_name
  replication_factor = 2
  partitions         = 3

  config = {
    "cleanup.policy" = "delete"
  }
}

resource "kafka_topic" "topic-2" {
  name               = var.confluentic_kafka_clean_topic_name
  replication_factor = 2
  partitions         = 3

  config = {
    "cleanup.policy" = "delete"
  }
}

output "kafka_url" {
  value = local.bootstrap_servers
}

output "key" {
  value     = confluentcloud_api_key.api_key.key
  sensitive = true
}

output "secret" {
  value     = confluentcloud_api_key.api_key.secret
  sensitive = true
}