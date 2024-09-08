package org.development.ecommerce.orderline;

import lombok.RequiredArgsConstructor;
import org.development.ecommerce.order.dto.OrderLineMapper;
import org.development.ecommerce.order.dto.OrderLineRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper mapper;
    public OrderLine saveOrderLine(OrderLineRequest request) {
        var order = mapper.toOrderLine(request);
        return orderLineRepository.save(order);
    }

    public List<OrderLineResponse> findAllByOrderId(UUID orderId) {
        return orderLineRepository.findAllByOrderId(orderId)
                .stream()
                .map(mapper::toOrderLineResponse)
                .toList();
    }
}
