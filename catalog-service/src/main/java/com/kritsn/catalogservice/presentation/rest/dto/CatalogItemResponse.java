package com.kritsn.catalogservice.presentation.rest.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record CatalogItemResponse(UUID id, String sku, String name, String category, String metal, String purity,
                                   BigDecimal grossWeightGrams, BigDecimal makingChargePercent, Instant createdAt) {
}
