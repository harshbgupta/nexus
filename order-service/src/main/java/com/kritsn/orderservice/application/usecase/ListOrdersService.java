package com.kritsn.orderservice.application.usecase;

import com.kritsn.orderservice.domain.model.Order;
import com.kritsn.orderservice.domain.port.in.ListOrdersUseCase;
import com.kritsn.orderservice.domain.port.out.OrderRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListOrdersService implements ListOrdersUseCase {

    private final OrderRepositoryPort repository;

    public ListOrdersService(OrderRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<Order> listAll() {
        return repository.findAll();
    }
}
