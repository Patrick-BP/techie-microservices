package com.techie.microservices.inventory.dto;

public record InventoryRequest(String skuCode, int quantity) {
}
