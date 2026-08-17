package com.kritsn.catalogservice.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "catalog_items")
public class CatalogItemJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false, unique = true)
    private String sku;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private String metal;

    @Column(nullable = false)
    private String purity;

    @Column(name = "gross_weight_grams", nullable = false)
    private BigDecimal grossWeightGrams;

    @Column(name = "making_charge_percent", nullable = false)
    private BigDecimal makingChargePercent;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    protected CatalogItemJpaEntity() {
    }

    public CatalogItemJpaEntity(UUID id, String sku, String name, String category, String metal, String purity,
                                 BigDecimal grossWeightGrams, BigDecimal makingChargePercent, Instant createdAt) {
        this.id = id;
        this.sku = sku;
        this.name = name;
        this.category = category;
        this.metal = metal;
        this.purity = purity;
        this.grossWeightGrams = grossWeightGrams;
        this.makingChargePercent = makingChargePercent;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getMetal() {
        return metal;
    }

    public String getPurity() {
        return purity;
    }

    public BigDecimal getGrossWeightGrams() {
        return grossWeightGrams;
    }

    public BigDecimal getMakingChargePercent() {
        return makingChargePercent;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
