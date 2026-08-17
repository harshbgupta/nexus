package com.kritsn.catalogservice.domain.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class CatalogItem {

    private final UUID id;
    private final String sku;
    private final String name;
    private final String category;
    private final String metal;
    private final String purity;
    private final BigDecimal grossWeightGrams;
    private final BigDecimal makingChargePercent;
    private final Instant createdAt;

    public CatalogItem(UUID id, String sku, String name, String category, String metal, String purity,
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
