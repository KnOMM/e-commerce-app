package org.development.ecommerce.order.dto;

import org.development.ecommerce.order.Order;
import org.development.ecommerce.orderline.OrderLine;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(UUID.randomUUID())
                .quantity(request.quantity())
                .order(
                        Order.builder()
                                .id(request.orderId())
                                .build()
                )
                .productId(request.productId())
                .build();
    }
}
