package com.kritsn.orderservice.domain.port.in;

import com.kritsn.orderservice.domain.model.Order;

import java.util.UUID;

public interface PlaceOrderUseCase {
    Order place(UUID customerId, String sku, int quantity);
}
