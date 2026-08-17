package com.kritsn.pricingservice.application.usecase;

import com.kritsn.pricingservice.domain.model.MetalRate;
import com.kritsn.pricingservice.domain.port.in.CreateMetalRateUseCase;
import com.kritsn.pricingservice.domain.port.out.MetalRateRepositoryPort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Service
public class CreateMetalRateService implements CreateMetalRateUseCase {

    private final MetalRateRepositoryPort repository;

    public CreateMetalRateService(MetalRateRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public MetalRate create(String metal, String purity, BigDecimal ratePerGram, Instant effectiveAt) {
        MetalRate rate = new MetalRate(UUID.randomUUID(), metal, purity, ratePerGram, effectiveAt, Instant.now());
        return repository.save(rate);
    }
}
