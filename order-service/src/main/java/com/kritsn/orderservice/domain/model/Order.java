package com.kritsn.orderservice.domain.model;

import java.time.Instant;
import java.util.UUID;

public class Order {

    private final UUID id;
    private final UUID customerId;
    private final String sku;
    private final int quantity;
    private final String status;
    private final Instant createdAt;

    public Order(UUID id, UUID customerId, String sku, int quantity, String status, Instant createdAt) {
        this.id = id;
        this.customerId = customerId;
        this.sku = sku;
        this.quantity = quantity;
        this.status = status;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public String getSku() {
        return sku;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
