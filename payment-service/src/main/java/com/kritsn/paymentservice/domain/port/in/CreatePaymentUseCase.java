package com.kritsn.paymentservice.domain.port.in;

import com.kritsn.paymentservice.domain.model.Payment;

import java.math.BigDecimal;
import java.util.UUID;

public interface CreatePaymentUseCase {
    Payment create(UUID orderId, BigDecimal amount);
}
