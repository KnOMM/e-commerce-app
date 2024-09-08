package org.development.ecommerce.payment;

import lombok.RequiredArgsConstructor;
import org.development.ecommerce.payment.dto.PaymentRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService service;

    @PostMapping
    public ResponseEntity<UUID> createPayment(
            @RequestBody PaymentRequest request
    ) {
        return ResponseEntity.ok(service.createPayment(request));
    }

    @GetMapping
    public ResponseEntity<List<Payment>> getPayments() {
        return ResponseEntity.ok(service.getAllPayments());
    }
}
