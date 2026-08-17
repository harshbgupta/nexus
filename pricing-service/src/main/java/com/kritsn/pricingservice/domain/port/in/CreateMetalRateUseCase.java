package com.kritsn.pricingservice.domain.port.in;

import com.kritsn.pricingservice.domain.model.MetalRate;

import java.math.BigDecimal;
import java.time.Instant;

public interface CreateMetalRateUseCase {
    MetalRate create(String metal, String purity, BigDecimal ratePerGram, Instant effectiveAt);
}
