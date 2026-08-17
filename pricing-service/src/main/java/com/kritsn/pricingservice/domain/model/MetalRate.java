package com.kritsn.pricingservice.domain.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class MetalRate {

    private final UUID id;
    private final String metal;
    private final String purity;
    private final BigDecimal ratePerGram;
    private final Instant effectiveAt;
    private final Instant createdAt;

    public MetalRate(UUID id, String metal, String purity, BigDecimal ratePerGram, Instant effectiveAt, Instant createdAt) {
        this.id = id;
        this.metal = metal;
        this.purity = purity;
        this.ratePerGram = ratePerGram;
        this.effectiveAt = effectiveAt;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public String getMetal() {
        return metal;
    }

    public String getPurity() {
        return purity;
    }

    public BigDecimal getRatePerGram() {
        return ratePerGram;
    }

    public Instant getEffectiveAt() {
        return effectiveAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
