package com.kritsn.inventoryservice.domain.port.in;

import com.kritsn.inventoryservice.domain.model.StockItem;

import java.util.List;

public interface ListStockItemsUseCase {
    List<StockItem> listAll();
}
