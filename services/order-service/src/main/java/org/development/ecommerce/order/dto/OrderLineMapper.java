package org.development.ecommerce.order.dto;

import org.development.ecommerce.orderline.OrderLine;
import org.development.ecommerce.orderline.OrderLineResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(UUID.randomUUID())
                .quantity(request.quantity())
                .productId(request.productId())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(orderLine.getId(), orderLine.getQuantity());
    }
}
