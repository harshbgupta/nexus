package com.kritsn.catalogservice.domain.port.in;

import com.kritsn.catalogservice.domain.model.CatalogItem;

import java.util.UUID;

public interface GetCatalogItemUseCase {
    CatalogItem getById(UUID id);
}
