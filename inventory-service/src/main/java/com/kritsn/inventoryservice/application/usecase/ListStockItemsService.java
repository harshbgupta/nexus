package com.kritsn.inventoryservice.application.usecase;

import com.kritsn.inventoryservice.domain.model.StockItem;
import com.kritsn.inventoryservice.domain.port.in.ListStockItemsUseCase;
import com.kritsn.inventoryservice.domain.port.out.StockItemRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListStockItemsService implements ListStockItemsUseCase {

    private final StockItemRepositoryPort repository;

    public ListStockItemsService(StockItemRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<StockItem> listAll() {
        return repository.findAll();
    }
}
