package com.kritsn.paymentservice.domain.port.in;

import com.kritsn.paymentservice.domain.model.Payment;

import java.util.List;

public interface ListPaymentsUseCase {
    List<Payment> listAll();
}
