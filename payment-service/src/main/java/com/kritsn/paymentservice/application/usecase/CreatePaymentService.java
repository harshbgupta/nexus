package com.kritsn.paymentservice.application.usecase;

import com.kritsn.paymentservice.domain.model.Payment;
import com.kritsn.paymentservice.domain.port.in.CreatePaymentUseCase;
import com.kritsn.paymentservice.domain.port.out.PaymentRepositoryPort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CreatePaymentService implements CreatePaymentUseCase {

    private final PaymentRepositoryPort repository;

    public CreatePaymentService(PaymentRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public Payment create(UUID orderId, BigDecimal amount) {
        // Simulated gateway outcome — a real integration replaces this coin flip.
        String status = ThreadLocalRandom.current().nextInt(100) < 85 ? "SUCCESS" : "FAILED";
        Payment payment = new Payment(UUID.randomUUID(), orderId, amount, status, Instant.now());
        return repository.save(payment);
    }
}
