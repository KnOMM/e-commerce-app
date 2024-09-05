package org.development.ecommerce.order.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.development.ecommerce.product.ProductPurchaseRequest;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest (
        String reference,
        @Positive(message = "Order amount should be positive")
        BigDecimal amount,
        @NotNull(message = "Payment method should not be null")
        PaymentMethod paymentMethod,
        @NotNull
        String customerId,
        @NotEmpty(message = "At lease one product")
        List<ProductPurchaseRequest> products
){
}
