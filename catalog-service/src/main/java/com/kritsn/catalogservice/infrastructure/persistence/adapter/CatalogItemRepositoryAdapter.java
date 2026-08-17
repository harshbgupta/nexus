package com.kritsn.catalogservice.infrastructure.persistence.adapter;

import com.kritsn.catalogservice.domain.model.CatalogItem;
import com.kritsn.catalogservice.domain.port.out.CatalogItemRepositoryPort;
import com.kritsn.catalogservice.infrastructure.persistence.entity.CatalogItemJpaEntity;
import com.kritsn.catalogservice.infrastructure.persistence.repository.CatalogItemJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class CatalogItemRepositoryAdapter implements CatalogItemRepositoryPort {

    private final CatalogItemJpaRepository jpaRepository;

    public CatalogItemRepositoryAdapter(CatalogItemJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public CatalogItem save(CatalogItem item) {
        return toDomain(jpaRepository.save(toEntity(item)));
    }

    @Override
    public Optional<CatalogItem> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<CatalogItem> findAll() {
        return jpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    private CatalogItemJpaEntity toEntity(CatalogItem item) {
        return new CatalogItemJpaEntity(item.getId(), item.getSku(), item.getName(), item.getCategory(),
                item.getMetal(), item.getPurity(), item.getGrossWeightGrams(), item.getMakingChargePercent(),
                item.getCreatedAt());
    }

    private CatalogItem toDomain(CatalogItemJpaEntity entity) {
        return new CatalogItem(entity.getId(), entity.getSku(), entity.getName(), entity.getCategory(),
                entity.getMetal(), entity.getPurity(), entity.getGrossWeightGrams(), entity.getMakingChargePercent(),
                entity.getCreatedAt());
    }
}
