package com.kritsn.inventoryservice.domain.port.in;

import com.kritsn.inventoryservice.domain.model.StockItem;

public interface CreateStockItemUseCase {
    StockItem create(String sku, String branch, int quantityAvailable);
}
