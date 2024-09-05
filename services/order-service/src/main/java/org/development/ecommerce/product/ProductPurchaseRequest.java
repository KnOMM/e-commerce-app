package org.development.ecommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record ProductPurchaseRequest(
        @NotNull
        UUID productId,
        @Positive(message = "Incorrect quantity")
        long quantity
) {
}

