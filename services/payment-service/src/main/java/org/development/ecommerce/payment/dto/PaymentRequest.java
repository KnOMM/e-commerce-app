package org.development.ecommerce.payment.dto;

import org.development.ecommerce.customer.Customer;
import org.development.ecommerce.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentRequest(
        UUID id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        UUID orderId,
        String orderReference,
        Customer customer
) {
}
