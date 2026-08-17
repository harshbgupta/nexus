package com.kritsn.orderservice.application.usecase;

import com.kritsn.orderservice.domain.model.Order;
import com.kritsn.orderservice.domain.port.in.PlaceOrderUseCase;
import com.kritsn.orderservice.domain.port.out.OrderRepositoryPort;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class PlaceOrderService implements PlaceOrderUseCase {

    private final OrderRepositoryPort repository;

    public PlaceOrderService(OrderRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Order place(UUID customerId, String sku, int quantity) {
        Order order = new Order(UUID.randomUUID(), customerId, sku, quantity, "PLACED", Instant.now());
        return repository.save(order);
    }
}
