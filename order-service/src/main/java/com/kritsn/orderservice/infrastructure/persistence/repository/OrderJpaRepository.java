package com.kritsn.orderservice.infrastructure.persistence.repository;

import com.kritsn.orderservice.infrastructure.persistence.entity.OrderJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderJpaRepository extends JpaRepository<OrderJpaEntity, UUID> {
}
