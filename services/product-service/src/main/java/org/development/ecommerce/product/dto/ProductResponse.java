package org.development.ecommerce.product.dto;

import java.math.BigDecimal;

public record ProductResponse (
        String name,
        double availableQuantity,
        BigDecimal price
){
}
