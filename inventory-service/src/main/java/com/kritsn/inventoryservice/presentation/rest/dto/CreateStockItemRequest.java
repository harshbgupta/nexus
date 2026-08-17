package com.kritsn.inventoryservice.presentation.rest.dto;

public record CreateStockItemRequest(String sku, String branch, int quantityAvailable) {
}
