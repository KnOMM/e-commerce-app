package org.development.ecommerce.order;

import org.development.ecommerce.order.dto.PaymentMethod;

import java.util.List;
import java.util.UUID;

public record OrderDTO (
        UUID orderId,
        String reference,
        Long totalAmount,
        PaymentMethod paymentMethod,
        String customerId,
        List<OrderLineDTO> orderLines
){
}
