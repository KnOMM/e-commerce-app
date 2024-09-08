package org.development.ecommerce.payment;

import org.development.ecommerce.payment.dto.PaymentRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentMapper {
    public Payment toPayment(PaymentRequest request){
        return Payment.builder()
                .id(UUID.randomUUID())
                .orderId(request.orderId())
                .paymentMethod(request.paymentMethod())
                .amount(request.amount())
                .build();
    }
}
