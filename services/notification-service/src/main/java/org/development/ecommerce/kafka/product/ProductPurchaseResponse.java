package org.development.ecommerce.kafka.product;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductPurchaseResponse(
        UUID id,
        String name,
        long quantity,
        BigDecimal price
) {
}

