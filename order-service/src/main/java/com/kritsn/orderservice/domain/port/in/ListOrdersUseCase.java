package com.kritsn.orderservice.domain.port.in;

import com.kritsn.orderservice.domain.model.Order;

import java.util.List;

public interface ListOrdersUseCase {
    List<Order> listAll();
}
