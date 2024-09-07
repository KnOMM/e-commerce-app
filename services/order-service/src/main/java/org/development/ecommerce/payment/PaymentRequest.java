package org.development.ecommerce.payment;

import org.development.ecommerce.customer.CustomerResponse;
import org.development.ecommerce.order.dto.PaymentMethod;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        UUID orderId,
        String orderReference,
        CustomerResponse customer
) {
}
