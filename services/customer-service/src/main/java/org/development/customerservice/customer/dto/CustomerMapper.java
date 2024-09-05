package org.development.customerservice.customer.dto;

import org.development.customerservice.customer.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {
    public Customer toCustomer(CustomerCreateRequest request) {
        if (request == null) {
            return null;
        }

        return Customer.builder()
                .email(request.email())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .address(request.address())
                .build();
    }

    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
                customer.getId()
                , customer.getFirstName()
                , customer.getLastName()
                , customer.getEmail()
                , customer.getAddress()
        );
    }
}
