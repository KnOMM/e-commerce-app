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
import org.development.ecommerce.orderline.OrderLine;
import org.development.ecommerce.orderline.OrderLineService;
import org.development.ecommerce.payment.PaymentClient;
import org.development.ecommerce.payment.PaymentRequest;
import org.development.ecommerce.product.ProductClient;
import org.development.ecommerce.product.ProductPurchaseRequest;
import org.development.ecommerce.product.ProductPurchaseResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private final PaymentClient paymentClient;

    public OrderDTO createOrder(OrderRequest orderRequest) {

        var customer = customerClient.findById(orderRequest.customerId())
                .orElseThrow(() -> new BusinessException("Customer not found, id: " + orderRequest.customerId()));

        List<ProductPurchaseResponse> productsList = productClient.getProductsList(orderRequest.products());
        Order order = this.orderRepository.save(mapper.toOrder(orderRequest));

        List<OrderLine> orderLines = new ArrayList<>();

        for (ProductPurchaseRequest purchaseRequest : orderRequest.products()) {
            var orderLine =  orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            UUID.randomUUID(),
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
            System.out.println(orderLine.getProductId());
            orderLines.add(orderLine);
        }

        var paymentRequest = new PaymentRequest(
                orderRequest.amount(),
                orderRequest.paymentMethod(),
                order.getId(),
                orderRequest.reference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequest.reference(),
                        orderRequest.amount(),
                        orderRequest.paymentMethod(),
                        customer,
                        productsList
                )
        );
        productsList.forEach(System.out::println);
        List<OrderLineDTO> orderLineDTOS = orderLines.stream()
                .map(ol -> new OrderLineDTO(ol.getId(), ol.getProductId(), ol.getQuantity(), productsList.stream().filter(p -> p.id().equals(ol.getProductId())).findAny().get().price()))
                .toList();

        return new OrderDTO(order.getId(),
                order.getReference(),
                order.getTotalAmount().longValue(),
                order.getPaymentMethod(),
                order.getCustomerId(),
                orderLineDTOS);
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
