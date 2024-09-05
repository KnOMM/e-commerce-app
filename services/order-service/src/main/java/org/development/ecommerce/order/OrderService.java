package org.development.ecommerce.order;

import lombok.RequiredArgsConstructor;
import org.development.ecommerce.customer.CustomerClient;
import org.development.ecommerce.exception.BusinessException;
import org.development.ecommerce.kafka.OrderConfirmation;
import org.development.ecommerce.kafka.OrderProducer;
import org.development.ecommerce.order.dto.OrderLineRequest;
import org.development.ecommerce.order.dto.OrderMapper;
import org.development.ecommerce.order.dto.OrderRequest;
import org.development.ecommerce.order.dto.OrderResponse;
import org.development.ecommerce.product.ProductClient;
import org.development.ecommerce.product.ProductPurchaseRequest;
import org.development.ecommerce.product.ProductPurchaseResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    public Order createOrder(OrderRequest orderRequest) {

        var customer = customerClient.findById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException("Customer not found, id: " + orderRequest.customerId()));

        List<ProductPurchaseResponse> productsList = productClient.getProductsList(orderRequest.products());
        var order = this.orderRepository.save(mapper.toOrder(orderRequest));

        for (ProductPurchaseRequest purchaseRequest: orderRequest.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequest.reference(),
                        orderRequest.amount(),
                        orderRequest.paymentMethod(),
                        customer,
                        productsList
                )
        );

        return order;
    }

    public List<OrderResponse> findAll() {
       return orderRepository.findAll()
               .stream()
               .map(mapper::fromOrder)
               .toList();
    }

    public OrderResponse findById(UUID orderId) {
        return orderRepository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new BusinessException("Order not found, id: " + orderId));
    }
}
