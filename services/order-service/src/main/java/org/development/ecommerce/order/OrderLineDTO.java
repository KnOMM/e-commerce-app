package org.development.ecommerce.order;

import java.util.UUID;

public record OrderLineDTO(
        UUID lineId,
        UUID productId,
        long quantity
) {
}
