package com.kritsn.catalogservice.application.usecase;

import com.kritsn.catalogservice.domain.model.CatalogItem;
import com.kritsn.catalogservice.domain.port.in.GetCatalogItemUseCase;
import com.kritsn.catalogservice.domain.port.out.CatalogItemRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class GetCatalogItemService implements GetCatalogItemUseCase {

    private final CatalogItemRepositoryPort repository;

    public GetCatalogItemService(CatalogItemRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public CatalogItem getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Catalog item not found: " + id));
    }
}
