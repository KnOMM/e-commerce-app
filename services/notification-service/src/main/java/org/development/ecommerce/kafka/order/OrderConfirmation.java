package org.development.ecommerce.kafka.order;

import org.development.ecommerce.kafka.payment.PaymentMethod;
import org.development.ecommerce.kafka.product.Product;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Customer customer,
        List<Product> productList
) {
}
