package com.kritsn.orderservice.presentation.rest.controller;

import com.kritsn.orderservice.domain.model.Order;
import com.kritsn.orderservice.domain.port.in.GetOrderUseCase;
import com.kritsn.orderservice.domain.port.in.ListOrdersUseCase;
import com.kritsn.orderservice.domain.port.in.PlaceOrderUseCase;
import com.kritsn.orderservice.presentation.rest.dto.OrderResponse;
import com.kritsn.orderservice.presentation.rest.dto.PlaceOrderRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final PlaceOrderUseCase placeOrderUseCase;
    private final GetOrderUseCase getOrderUseCase;
    private final ListOrdersUseCase listOrdersUseCase;

    public OrderController(PlaceOrderUseCase placeOrderUseCase, GetOrderUseCase getOrderUseCase,
                            ListOrdersUseCase listOrdersUseCase) {
        this.placeOrderUseCase = placeOrderUseCase;
        this.getOrderUseCase = getOrderUseCase;
        this.listOrdersUseCase = listOrdersUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse place(@RequestBody PlaceOrderRequest request) {
        Order order = placeOrderUseCase.place(request.customerId(), request.sku(), request.quantity());
        return toResponse(order);
    }

    @GetMapping("/{id}")
    public OrderResponse getById(@PathVariable UUID id) {
        return toResponse(getOrderUseCase.getById(id));
    }

    @GetMapping
    public List<OrderResponse> listAll() {
        return listOrdersUseCase.listAll().stream().map(this::toResponse).toList();
    }

    private OrderResponse toResponse(Order order) {
        return new OrderResponse(order.getId(), order.getCustomerId(), order.getSku(), order.getQuantity(),
                order.getStatus(), order.getCreatedAt());
    }
}
