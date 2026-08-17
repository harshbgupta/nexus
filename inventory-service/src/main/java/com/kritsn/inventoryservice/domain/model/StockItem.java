package com.kritsn.inventoryservice.domain.model;

import java.time.Instant;
import java.util.UUID;

public class StockItem {

    private final UUID id;
    private final String sku;
    private final String branch;
    private final int quantityAvailable;
    private final Instant createdAt;

    public StockItem(UUID id, String sku, String branch, int quantityAvailable, Instant createdAt) {
        this.id = id;
        this.sku = sku;
        this.branch = branch;
        this.quantityAvailable = quantityAvailable;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public String getBranch() {
        return branch;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
