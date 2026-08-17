package com.kritsn.pricingservice.presentation.rest.controller;

import com.kritsn.pricingservice.domain.model.MetalRate;
import com.kritsn.pricingservice.domain.port.in.CreateMetalRateUseCase;
import com.kritsn.pricingservice.domain.port.in.GetMetalRateUseCase;
import com.kritsn.pricingservice.domain.port.in.ListMetalRatesUseCase;
import com.kritsn.pricingservice.presentation.rest.dto.CreateMetalRateRequest;
import com.kritsn.pricingservice.presentation.rest.dto.MetalRateResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/pricing/rates")
public class PricingController {

    private final CreateMetalRateUseCase createMetalRateUseCase;
    private final GetMetalRateUseCase getMetalRateUseCase;
    private final ListMetalRatesUseCase listMetalRatesUseCase;

    public PricingController(CreateMetalRateUseCase createMetalRateUseCase,
                              GetMetalRateUseCase getMetalRateUseCase,
                              ListMetalRatesUseCase listMetalRatesUseCase) {
        this.createMetalRateUseCase = createMetalRateUseCase;
        this.getMetalRateUseCase = getMetalRateUseCase;
        this.listMetalRatesUseCase = listMetalRatesUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MetalRateResponse create(@RequestBody CreateMetalRateRequest request) {
        MetalRate rate = createMetalRateUseCase.create(request.metal(), request.purity(), request.ratePerGram(),
                request.effectiveAt());
        return toResponse(rate);
    }

    @GetMapping("/{id}")
    public MetalRateResponse getById(@PathVariable UUID id) {
        return toResponse(getMetalRateUseCase.getById(id));
    }

    @GetMapping
    public List<MetalRateResponse> listAll() {
        return listMetalRatesUseCase.listAll().stream().map(this::toResponse).toList();
    }

    private MetalRateResponse toResponse(MetalRate rate) {
        return new MetalRateResponse(rate.getId(), rate.getMetal(), rate.getPurity(), rate.getRatePerGram(),
                rate.getEffectiveAt(), rate.getCreatedAt());
    }
}
