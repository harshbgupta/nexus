package com.kritsn.pricingservice.application.usecase;

import com.kritsn.pricingservice.domain.model.MetalRate;
import com.kritsn.pricingservice.domain.port.in.ListMetalRatesUseCase;
import com.kritsn.pricingservice.domain.port.out.MetalRateRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListMetalRatesService implements ListMetalRatesUseCase {

    private final MetalRateRepositoryPort repository;

    public ListMetalRatesService(MetalRateRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<MetalRate> listAll() {
        return repository.findAll();
    }
}
