package com.kritsn.paymentservice.infrastructure.persistence.adapter;

import com.kritsn.paymentservice.domain.model.Payment;
import com.kritsn.paymentservice.domain.port.out.PaymentRepositoryPort;
import com.kritsn.paymentservice.infrastructure.persistence.entity.PaymentJpaEntity;
import com.kritsn.paymentservice.infrastructure.persistence.repository.PaymentJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class PaymentRepositoryAdapter implements PaymentRepositoryPort {

    private final PaymentJpaRepository jpaRepository;

    public PaymentRepositoryAdapter(PaymentJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Payment save(Payment payment) {
        return toDomain(jpaRepository.save(toEntity(payment)));
    }

    @Override
    public Optional<Payment> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Payment> findAll() {
        return jpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    private PaymentJpaEntity toEntity(Payment payment) {
        return new PaymentJpaEntity(payment.getId(), payment.getOrderId(), payment.getAmount(), payment.getStatus(),
                payment.getCreatedAt());
    }

    private Payment toDomain(PaymentJpaEntity entity) {
        return new Payment(entity.getId(), entity.getOrderId(), entity.getAmount(), entity.getStatus(),
                entity.getCreatedAt());
    }
}
