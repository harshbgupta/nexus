package com.kritsn.catalogservice.infrastructure.persistence.repository;

import com.kritsn.catalogservice.infrastructure.persistence.entity.CatalogItemJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CatalogItemJpaRepository extends JpaRepository<CatalogItemJpaEntity, UUID> {
}
