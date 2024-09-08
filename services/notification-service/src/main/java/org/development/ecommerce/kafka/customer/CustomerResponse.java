package org.development.ecommerce.kafka.customer;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email
) {
}

