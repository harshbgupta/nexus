package com.kritsn.orderservice.presentation.rest.dto;

import java.time.Instant;
import java.util.UUID;

public record OrderResponse(UUID id, UUID customerId, String sku, int quantity, String status, Instant createdAt) {
}
