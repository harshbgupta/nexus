package com.kritsn.pricingservice.presentation.rest.dto;

import java.math.BigDecimal;
import java.time.Instant;

public record CreateMetalRateRequest(String metal, String purity, BigDecimal ratePerGram, Instant effectiveAt) {
}
