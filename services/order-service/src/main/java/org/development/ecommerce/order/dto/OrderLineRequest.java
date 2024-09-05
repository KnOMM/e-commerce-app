package org.development.ecommerce.order.dto;

import java.util.UUID;

public record OrderLineRequest(
        UUID id,
        UUID orderId,
        UUID productId,
        long quantity
) {
}
