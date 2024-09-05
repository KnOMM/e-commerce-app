package org.development.ecommerce.kafka;

import org.development.ecommerce.customer.CustomerResponse;
import org.development.ecommerce.order.dto.PaymentMethod;
import org.development.ecommerce.product.ProductPurchaseRequest;
import org.development.ecommerce.product.ProductPurchaseResponse;

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
