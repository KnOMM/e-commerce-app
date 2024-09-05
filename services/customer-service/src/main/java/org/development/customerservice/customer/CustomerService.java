package org.development.customerservice.customer;

import lombok.RequiredArgsConstructor;
import org.development.customerservice.customer.dto.CustomerMapper;
import org.development.customerservice.customer.dto.CustomerCreateRequest;
import org.development.customerservice.customer.dto.CustomerResponse;
import org.development.customerservice.customer.dto.CustomerUpdateRequest;
import org.development.customerservice.customer.exception.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public CustomerResponse createCustomer(CustomerCreateRequest request) {
        return mapper.fromCustomer(repository.save(mapper.toCustomer(request)));
    }

    public void updateCustomer(CustomerUpdateRequest request) {
        var customer = repository.findById(request.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        HttpStatus.NOT_FOUND,
                        String.format("Customer with id %s not found", request.id())
                ));

        mergeCustomer(customer, request);
        repository.save(customer);
    }

    private void mergeCustomer(Customer customer, CustomerUpdateRequest request) {
        customer.setFirstName(customer.getFirstName());
        customer.setLastName(customer.getLastName());
        customer.setEmail(customer.getEmail());
        if (request.address() != null) {
            customer.setAddress(request.address());
        }
    }

    public List<CustomerResponse> findAllCustomers() {
        return repository.findAll()
                .stream()
                .map(mapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public Boolean existsById(String customerId) {
        return repository.findById(customerId)
                .isPresent();
    }

    public Optional<CustomerResponse> findById(String customerId) {
        return repository.findById(customerId)
                .map(mapper::fromCustomer);
    }

    public void deleteCustomer(String customerId) {
        repository.findById(customerId)
                .orElseThrow(() ->
                        new CustomerNotFoundException(
                                HttpStatus.NOT_FOUND,
                                String.format("Customer with id %s not found", customerId)));

        repository.deleteById(customerId);
    }
}
