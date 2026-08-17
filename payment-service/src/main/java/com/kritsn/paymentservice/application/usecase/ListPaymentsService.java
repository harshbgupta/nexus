package com.kritsn.paymentservice.application.usecase;

import com.kritsn.paymentservice.domain.model.Payment;
import com.kritsn.paymentservice.domain.port.in.ListPaymentsUseCase;
import com.kritsn.paymentservice.domain.port.out.PaymentRepositoryPort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListPaymentsService implements ListPaymentsUseCase {

    private final PaymentRepositoryPort repository;

    public ListPaymentsService(PaymentRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<Payment> listAll() {
        return repository.findAll();
    }
}
