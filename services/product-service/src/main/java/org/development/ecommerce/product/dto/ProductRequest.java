package org.development.ecommerce.product.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductRequest(
        @NotEmpty(message = "Product name is required")
        String name,
        String description,
        @Positive(message = "Incorrect available quantity")
        long availableQuantity,
        @Positive(message = "Incorrect price")
        BigDecimal price,
        @NotEmpty(message = "Product must have category")
        UUID categoryId) {
}
