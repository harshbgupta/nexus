package com.kritsn.pricingservice.domain.port.in;

import com.kritsn.pricingservice.domain.model.MetalRate;

import java.util.List;

public interface ListMetalRatesUseCase {
    List<MetalRate> listAll();
}
