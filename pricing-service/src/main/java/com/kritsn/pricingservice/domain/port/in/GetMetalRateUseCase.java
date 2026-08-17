package com.kritsn.pricingservice.domain.port.in;

import com.kritsn.pricingservice.domain.model.MetalRate;

import java.util.UUID;

public interface GetMetalRateUseCase {
    MetalRate getById(UUID id);
}
