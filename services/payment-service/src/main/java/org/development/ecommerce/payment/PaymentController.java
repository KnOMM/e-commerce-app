package org.development.ecommerce.payment;

import lombok.RequiredArgsConstructor;
import org.development.ecommerce.payment.dto.PaymentRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/payments")
public class PaymentController {

    private final PaymentService service;

    @PostMapping
    public ResponseEntity<UUID> createPayment(
            @RequestBody PaymentRequest request
    ) {
        return ResponseEntity.ok(service.createPayment(request));
    }
}
