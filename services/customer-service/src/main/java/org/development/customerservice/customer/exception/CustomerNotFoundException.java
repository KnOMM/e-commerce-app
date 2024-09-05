package org.development.customerservice.customer.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

public class CustomerNotFoundException extends ResponseStatusException {
    public CustomerNotFoundException(HttpStatusCode status, String message) {
        super(status, message);
    }
}
