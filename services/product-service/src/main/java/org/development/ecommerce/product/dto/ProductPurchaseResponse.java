package org.development.ecommerce.product.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductPurchaseResponse(
        UUID id,
        String name,
        long quantity,
        BigDecimal price
) {
}
