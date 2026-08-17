package com.kritsn.inventoryservice.application.usecase;

import com.kritsn.inventoryservice.domain.model.StockItem;
import com.kritsn.inventoryservice.domain.port.in.GetStockItemUseCase;
import com.kritsn.inventoryservice.domain.port.out.StockItemRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class GetStockItemService implements GetStockItemUseCase {

    private final StockItemRepositoryPort repository;

    public GetStockItemService(StockItemRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public StockItem getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Stock item not found: " + id));
    }
}
