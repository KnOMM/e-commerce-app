package org.development.ecommerce.kafka.order;

import org.development.ecommerce.kafka.customer.CustomerResponse;
import org.development.ecommerce.kafka.payment.PaymentMethod;
import org.development.ecommerce.kafka.product.ProductPurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal totalPrice,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<ProductPurchaseResponse> products
) {
}
