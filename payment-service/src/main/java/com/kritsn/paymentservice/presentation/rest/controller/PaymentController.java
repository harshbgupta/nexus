package com.kritsn.paymentservice.presentation.rest.controller;

import com.kritsn.paymentservice.domain.model.Payment;
import com.kritsn.paymentservice.domain.port.in.CreatePaymentUseCase;
import com.kritsn.paymentservice.domain.port.in.GetPaymentUseCase;
import com.kritsn.paymentservice.domain.port.in.ListPaymentsUseCase;
import com.kritsn.paymentservice.presentation.rest.dto.CreatePaymentRequest;
import com.kritsn.paymentservice.presentation.rest.dto.PaymentResponse;
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
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final CreatePaymentUseCase createPaymentUseCase;
    private final GetPaymentUseCase getPaymentUseCase;
    private final ListPaymentsUseCase listPaymentsUseCase;

    public PaymentController(CreatePaymentUseCase createPaymentUseCase, GetPaymentUseCase getPaymentUseCase,
                              ListPaymentsUseCase listPaymentsUseCase) {
        this.createPaymentUseCase = createPaymentUseCase;
        this.getPaymentUseCase = getPaymentUseCase;
        this.listPaymentsUseCase = listPaymentsUseCase;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResponse create(@RequestBody CreatePaymentRequest request) {
        Payment payment = createPaymentUseCase.create(request.orderId(), request.amount());
        return toResponse(payment);
    }

    @GetMapping("/{id}")
    public PaymentResponse getById(@PathVariable UUID id) {
        return toResponse(getPaymentUseCase.getById(id));
    }

    @GetMapping
    public List<PaymentResponse> listAll() {
        return listPaymentsUseCase.listAll().stream().map(this::toResponse).toList();
    }

    private PaymentResponse toResponse(Payment payment) {
        return new PaymentResponse(payment.getId(), payment.getOrderId(), payment.getAmount(), payment.getStatus(),
                payment.getCreatedAt());
    }
}
