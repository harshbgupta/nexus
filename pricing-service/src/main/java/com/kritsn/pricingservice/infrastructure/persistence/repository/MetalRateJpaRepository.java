package com.kritsn.pricingservice.infrastructure.persistence.repository;

import com.kritsn.pricingservice.infrastructure.persistence.entity.MetalRateJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MetalRateJpaRepository extends JpaRepository<MetalRateJpaEntity, UUID> {
}
