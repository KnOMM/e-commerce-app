package org.development.ecommerce.notification;

import lombok.*;
import org.development.ecommerce.kafka.OrderConfirmation;
import org.development.ecommerce.kafka.payment.PaymentConfirmation;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Notification {
    @Id
    private String id;
    private NotificationType notificationType;
    private LocalDateTime timestamp;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;
}
