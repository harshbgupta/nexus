package com.kritsn.catalogservice.domain.port.in;

import com.kritsn.catalogservice.domain.model.CatalogItem;

import java.util.List;

public interface ListCatalogItemsUseCase {
    List<CatalogItem> listAll();
}
