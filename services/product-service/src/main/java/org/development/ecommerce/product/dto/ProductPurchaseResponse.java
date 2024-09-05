package org.development.ecommerce.product.dto;

import java.util.UUID;

public record ProductPurchaseResponse(
        UUID id,
        String name,
        long quantity
) {
}
