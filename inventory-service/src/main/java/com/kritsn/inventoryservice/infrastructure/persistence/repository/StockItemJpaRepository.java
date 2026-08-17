package com.kritsn.inventoryservice.infrastructure.persistence.repository;

import com.kritsn.inventoryservice.infrastructure.persistence.entity.StockItemJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StockItemJpaRepository extends JpaRepository<StockItemJpaEntity, UUID> {
}
