package org.development.customerservice.customer.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.development.customerservice.customer.Address;

public record CustomerCreateRequest(
        @NotEmpty(message = "Customer firstname is required")
        String firstName,
        @NotEmpty(message = "Customer lastname is required")
        String lastName,
        @NotEmpty(message = "Customer email is required")
        @Email(message = "Customer email is not valid")
        String email,
        Address address
) {
}
