package org.development.ecommerce.order;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.development.ecommerce.order.dto.OrderRequest;
import org.development.ecommerce.order.dto.OrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody @Valid OrderRequest order){

        return ResponseEntity.created(null).body(service.createOrder(order));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrders(){
        System.out.println("getOrders");
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable UUID orderId){
        return ResponseEntity.ok(service.findById(orderId));
    }
}
