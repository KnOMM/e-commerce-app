package org.development.customerservice.customer.dto;

import org.development.customerservice.customer.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerMapper {
    private static final Logger log = LoggerFactory.getLogger(CustomerMapper.class);

    public Customer toCustomer(CustomerCreateRequest request) {
        if (request == null) {
            return null;
        }

        return Customer.builder()
                .id(UUID.randomUUID().toString())
                .email(request.email())
                .firstName(request.firstName())
                .lastName(request.lastName())
                .address(request.address())
                .build();
    }

    public CustomerResponse fromCustomer(Customer customer) {
        log.warn(customer.getId());
        return new CustomerResponse(
                customer.getId()
                , customer.getFirstName()
                , customer.getLastName()
                , customer.getEmail()
                , customer.getAddress()
        );
    }
}
