package com.kritsn.catalogservice.presentation.rest.controller;

import com.kritsn.catalogservice.domain.model.CatalogItem;
import com.kritsn.catalogservice.domain.port.in.CreateCatalogItemUseCase;
import com.kritsn.catalogservice.domain.port.in.GetCatalogItemUseCase;
import com.kritsn.catalogservice.domain.port.in.ListCatalogItemsUseCase;
import com.kritsn.catalogservice.presentation.rest.dto.CatalogItemResponse;
import com.kritsn.catalogservice.presentation.rest.dto.CreateCatalogItemRequest;
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
@RequestMapping("/api/v1/catalog")
public class CatalogController {

    private final CreateCatalogItemUseCase createCatalogItemUseCase;
    private final GetCatalogItemUseCase getCatalogItemUseCase;
    private final ListCatalogItemsUseCase listCatalogItemsUseCase;

    public CatalogController(CreateCatalogItemUseCase createCatalogItemUseCase,
                              GetCatalogItemUseCase getCatalogItemUseCase,
                              ListCatalogItemsUseCase listCatalogItemsUseCase) {
        this.createCatalogItemUseCase = createCatalogItemUseCase;
        this.getCatalogItemUseCase = getCatalogItemUseCase;
        this.listCatalogItemsUseCase = listCatalogItemsUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CatalogItemResponse create(@RequestBody CreateCatalogItemRequest request) {
        CatalogItem item = createCatalogItemUseCase.create(request.sku(), request.name(), request.category(),
                request.metal(), request.purity(), request.grossWeightGrams(), request.makingChargePercent());
        return toResponse(item);
    }

    @GetMapping("/{id}")
    public CatalogItemResponse getById(@PathVariable UUID id) {
        return toResponse(getCatalogItemUseCase.getById(id));
    }

    @GetMapping
    public List<CatalogItemResponse> listAll() {
        return listCatalogItemsUseCase.listAll().stream().map(this::toResponse).toList();
    }

    private CatalogItemResponse toResponse(CatalogItem item) {
        return new CatalogItemResponse(item.getId(), item.getSku(), item.getName(), item.getCategory(),
                item.getMetal(), item.getPurity(), item.getGrossWeightGrams(), item.getMakingChargePercent(),
                item.getCreatedAt());
    }
}
