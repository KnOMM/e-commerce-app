package org.development.ecommerce.payment;

import lombok.RequiredArgsConstructor;
import org.development.ecommerce.notification.NotificationProducer;
import org.development.ecommerce.notification.PaymentNotificationRequest;
import org.development.ecommerce.payment.dto.PaymentRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository repository;
    private final PaymentMapper mapper;

    private final NotificationProducer notificationProducer;

    public UUID createPayment(PaymentRequest request) {
        var payment = repository.save(mapper.toPayment(request));
        notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstname(),
                        request.customer().lastname(),
                        request.customer().email()
                )
        );
        return payment.getId();
    }
}
