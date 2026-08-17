package com.kritsn.catalogservice.presentation.rest.dto;

import java.math.BigDecimal;

public record CreateCatalogItemRequest(String sku, String name, String category, String metal, String purity,
                                        BigDecimal grossWeightGrams, BigDecimal makingChargePercent) {
}
