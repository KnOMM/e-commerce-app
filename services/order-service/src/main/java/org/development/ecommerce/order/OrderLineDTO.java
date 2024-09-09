package org.development.ecommerce.order;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderLineDTO(
        UUID lineId,
        UUID productId,
        long quantity,
        BigDecimal unitPrice
) {
}
