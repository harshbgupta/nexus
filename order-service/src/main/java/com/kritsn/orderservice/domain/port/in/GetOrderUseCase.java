package com.kritsn.orderservice.domain.port.in;

import com.kritsn.orderservice.domain.model.Order;

import java.util.UUID;

public interface GetOrderUseCase {
    Order getById(UUID id);
}
