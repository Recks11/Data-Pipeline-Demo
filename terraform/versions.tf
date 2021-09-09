terraform {
  required_providers {
    confluentcloud = {
      source = "Mongey/confluentcloud"
    }
    kafka = {
      source  = "Mongey/kafka"
      version = "0.3.3"
    }
  }
}