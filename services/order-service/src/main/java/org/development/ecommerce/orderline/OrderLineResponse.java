package org.development.ecommerce.orderline;

import java.util.UUID;

public record OrderLineResponse(
        UUID orderId,
        double quantity
) {
}
