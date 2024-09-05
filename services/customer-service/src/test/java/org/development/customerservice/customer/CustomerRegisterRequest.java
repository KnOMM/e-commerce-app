package org.development.customerservice.customer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomerRegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
}
