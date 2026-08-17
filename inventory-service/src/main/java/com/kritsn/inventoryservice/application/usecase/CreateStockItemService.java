package com.kritsn.inventoryservice.application.usecase;

import com.kritsn.inventoryservice.domain.model.StockItem;
import com.kritsn.inventoryservice.domain.port.in.CreateStockItemUseCase;
import com.kritsn.inventoryservice.domain.port.out.StockItemRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class CreateStockItemService implements CreateStockItemUseCase {

    private final StockItemRepositoryPort repository;

    public CreateStockItemService(StockItemRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public StockItem create(String sku, String branch, int quantityAvailable) {
        StockItem item = new StockItem(UUID.randomUUID(), sku, branch, quantityAvailable, Instant.now());
        return repository.save(item);
    }
}
