package org.development.ecommerce.order;

import lombok.RequiredArgsConstructor;
import org.development.ecommerce.order.dto.OrderLineMapper;
import org.development.ecommerce.order.dto.OrderLineRequest;
import org.development.ecommerce.orderline.OrderLine;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper mapper;
    public OrderLine saveOrderLine(OrderLineRequest request) {
        var order = mapper.toOrderLine(request);
        return orderLineRepository.save(order);
    }
}
