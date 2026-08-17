package com.kritsn.paymentservice.presentation.rest.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CreatePaymentRequest(UUID orderId, BigDecimal amount) {
}
