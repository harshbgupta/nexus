package com.kritsn.catalogservice.application.usecase;

import com.kritsn.catalogservice.domain.model.CatalogItem;
import com.kritsn.catalogservice.domain.port.in.CreateCatalogItemUseCase;
import com.kritsn.catalogservice.domain.port.out.CatalogItemRepositoryPort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Service
public class CreateCatalogItemService implements CreateCatalogItemUseCase {

    private final CatalogItemRepositoryPort repository;

    public CreateCatalogItemService(CatalogItemRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public CatalogItem create(String sku, String name, String category, String metal, String purity,
                               BigDecimal grossWeightGrams, BigDecimal makingChargePercent) {
        CatalogItem item = new CatalogItem(UUID.randomUUID(), sku, name, category, metal, purity,
                grossWeightGrams, makingChargePercent, Instant.now());
        return repository.save(item);
    }
}
