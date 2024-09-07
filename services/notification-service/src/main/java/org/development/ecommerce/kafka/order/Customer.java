package org.development.ecommerce.kafka.order;


import java.util.UUID;

public record Customer(
        UUID id,
        String firstname,
        String lastname,
        String email
) {
}
