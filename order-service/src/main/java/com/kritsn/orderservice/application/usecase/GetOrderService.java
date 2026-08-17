package com.kritsn.orderservice.application.usecase;

import com.kritsn.orderservice.domain.model.Order;
import com.kritsn.orderservice.domain.port.in.GetOrderUseCase;
import com.kritsn.orderservice.domain.port.out.OrderRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class GetOrderService implements GetOrderUseCase {

    private final OrderRepositoryPort repository;

    public GetOrderService(OrderRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Order getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Order not found: " + id));
    }
}
