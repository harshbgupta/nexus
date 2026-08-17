package com.kritsn.paymentservice.infrastructure.persistence.repository;

import com.kritsn.paymentservice.infrastructure.persistence.entity.PaymentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentJpaRepository extends JpaRepository<PaymentJpaEntity, UUID> {
}
