package com.kritsn.paymentservice.domain.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class Payment {

    private final UUID id;
    private final UUID orderId;
    private final BigDecimal amount;
    private final String status;
    private final Instant createdAt;

    public Payment(UUID id, UUID orderId, BigDecimal amount, String status, Instant createdAt) {
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
