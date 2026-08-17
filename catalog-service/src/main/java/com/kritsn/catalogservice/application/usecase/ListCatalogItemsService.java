package com.kritsn.catalogservice.application.usecase;

import com.kritsn.catalogservice.domain.model.CatalogItem;
import com.kritsn.catalogservice.domain.port.in.ListCatalogItemsUseCase;
import com.kritsn.catalogservice.domain.port.out.CatalogItemRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListCatalogItemsService implements ListCatalogItemsUseCase {

    private final CatalogItemRepositoryPort repository;

    public ListCatalogItemsService(CatalogItemRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<CatalogItem> listAll() {
        return repository.findAll();
    }
}
