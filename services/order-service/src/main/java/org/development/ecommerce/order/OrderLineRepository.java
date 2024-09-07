package org.development.ecommerce.order;

import org.development.ecommerce.orderline.OrderLine;
import org.development.ecommerce.orderline.OrderLineResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderLineRepository extends JpaRepository<OrderLine, UUID> {
    List<OrderLine> findAllByOrderId(UUID orderId);
}
