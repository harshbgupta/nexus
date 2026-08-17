package com.kritsn.inventoryservice.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "stock_items")
public class StockItemJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String sku;

    @Column(nullable = false)
    private String branch;

    @Column(name = "quantity_available", nullable = false)
    private int quantityAvailable;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    protected StockItemJpaEntity() {
    }

    public StockItemJpaEntity(UUID id, String sku, String branch, int quantityAvailable, Instant createdAt) {
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
