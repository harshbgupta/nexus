package com.kritsn.pricingservice.application.usecase;

import com.kritsn.pricingservice.domain.model.MetalRate;
import com.kritsn.pricingservice.domain.port.in.GetMetalRateUseCase;
import com.kritsn.pricingservice.domain.port.out.MetalRateRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class GetMetalRateService implements GetMetalRateUseCase {

    private final MetalRateRepositoryPort repository;

    public GetMetalRateService(MetalRateRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public MetalRate getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Metal rate not found: " + id));
    }
}
