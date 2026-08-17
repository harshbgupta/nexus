package com.kritsn.orderservice.presentation.rest.dto;

import java.util.UUID;

public record PlaceOrderRequest(UUID customerId, String sku, int quantity) {
}
