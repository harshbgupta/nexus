package com.kritsn.orderservice.infrastructure.persistence.adapter;

import com.kritsn.orderservice.domain.model.Order;
import com.kritsn.orderservice.domain.port.out.OrderRepositoryPort;
import com.kritsn.orderservice.infrastructure.persistence.entity.OrderJpaEntity;
import com.kritsn.orderservice.infrastructure.persistence.repository.OrderJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class OrderRepositoryAdapter implements OrderRepositoryPort {

    private final OrderJpaRepository jpaRepository;

    public OrderRepositoryAdapter(OrderJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Order save(Order order) {
        return toDomain(jpaRepository.save(toEntity(order)));
    }

    @Override
    public Optional<Order> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Order> findAll() {
        return jpaRepository.findAll().stream().map(this::toDomain).toList();
    }

    private OrderJpaEntity toEntity(Order order) {
        return new OrderJpaEntity(order.getId(), order.getCustomerId(), order.getSku(), order.getQuantity(),
                order.getStatus(), order.getCreatedAt());
    }

    private Order toDomain(OrderJpaEntity entity) {
        return new Order(entity.getId(), entity.getCustomerId(), entity.getSku(), entity.getQuantity(),
                entity.getStatus(), entity.getCreatedAt());
    }
}
