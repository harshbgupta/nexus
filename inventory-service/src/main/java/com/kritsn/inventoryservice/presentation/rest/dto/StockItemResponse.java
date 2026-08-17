package com.kritsn.inventoryservice.presentation.rest.dto;

import java.time.Instant;
import java.util.UUID;

public record StockItemResponse(UUID id, String sku, String branch, int quantityAvailable, Instant createdAt) {
}
