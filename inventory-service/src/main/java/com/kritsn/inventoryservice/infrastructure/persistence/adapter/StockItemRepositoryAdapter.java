package com.kritsn.inventoryservice.infrastructure.persistence.adapter;

import com.kritsn.inventoryservice.domain.model.StockItem;
import com.kritsn.inventoryservice.domain.port.out.StockItemRepositoryPort;
import com.kritsn.inventoryservice.infrastructure.persistence.entity.StockItemJpaEntity;
import com.kritsn.inventoryservice.infrastructure.persistence.repository.StockItemJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class StockItemRepositoryAdapter implements StockItemRepositoryPort {

    private final StockItemJpaRepository jpaRepository;

    public StockItemRepositoryAdapter(StockItemJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public StockItem save(StockItem item) {
        return toDomain(jpaRepository.save(toEntity(item)));
    }

    @Override
    public Optional<StockItem> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<StockItem> findAll() {
        return jpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    private StockItemJpaEntity toEntity(StockItem item) {
        return new StockItemJpaEntity(item.getId(), item.getSku(), item.getBranch(), item.getQuantityAvailable(),
                item.getCreatedAt());
    }

    private StockItem toDomain(StockItemJpaEntity entity) {
        return new StockItem(entity.getId(), entity.getSku(), entity.getBranch(), entity.getQuantityAvailable(),
                entity.getCreatedAt());
    }
}
