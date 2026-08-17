package com.kritsn.inventoryservice.domain.port.in;

import com.kritsn.inventoryservice.domain.model.StockItem;

import java.util.UUID;

public interface GetStockItemUseCase {
    StockItem getById(UUID id);
}
