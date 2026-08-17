package com.kritsn.pricingservice.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "metal_rates")
public class MetalRateJpaEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String metal;

    @Column(nullable = false)
    private String purity;

    @Column(name = "rate_per_gram", nullable = false)
    private BigDecimal ratePerGram;

    @Column(name = "effective_at", nullable = false)
    private Instant effectiveAt;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    protected MetalRateJpaEntity() {
    }

    public MetalRateJpaEntity(UUID id, String metal, String purity, BigDecimal ratePerGram, Instant effectiveAt, Instant createdAt) {
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
