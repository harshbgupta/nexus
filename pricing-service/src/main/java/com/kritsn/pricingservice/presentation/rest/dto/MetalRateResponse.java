package com.kritsn.pricingservice.presentation.rest.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record MetalRateResponse(UUID id, String metal, String purity, BigDecimal ratePerGram, Instant effectiveAt,
                                 Instant createdAt) {
}
