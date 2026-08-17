package com.kritsn.paymentservice.application.usecase;

import com.kritsn.paymentservice.domain.model.Payment;
import com.kritsn.paymentservice.domain.port.in.GetPaymentUseCase;
import com.kritsn.paymentservice.domain.port.out.PaymentRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class GetPaymentService implements GetPaymentUseCase {

    private final PaymentRepositoryPort repository;

    public GetPaymentService(PaymentRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Payment getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Payment not found: " + id));
    }
}
