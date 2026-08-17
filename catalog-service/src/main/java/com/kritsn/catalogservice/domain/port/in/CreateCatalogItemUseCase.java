package com.kritsn.catalogservice.domain.port.in;

import com.kritsn.catalogservice.domain.model.CatalogItem;

import java.math.BigDecimal;

public interface CreateCatalogItemUseCase {
    CatalogItem create(String sku, String name, String category, String metal, String purity,
                        BigDecimal grossWeightGrams, BigDecimal makingChargePercent);
}
