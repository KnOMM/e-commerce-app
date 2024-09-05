package org.development.customerservice.customer.dto;

import org.development.customerservice.customer.Address;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email,
        Address address
) {
}
