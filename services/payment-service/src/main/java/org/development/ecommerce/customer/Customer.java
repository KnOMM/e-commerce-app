package org.development.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Validated
public record Customer(
        UUID id,
        @NotNull
        String firstname,
        @NotNull
        String lastname,
        @NotNull
        @Email(message = "Valid email is required")
        String email
) {
}
