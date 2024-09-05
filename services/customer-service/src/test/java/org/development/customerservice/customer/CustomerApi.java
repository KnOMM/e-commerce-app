package org.development.customerservice.customer;

import org.development.customerservice.Helper;
import org.development.customerservice.customer.dto.CustomerUpdateRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.net.URI;

@Lazy
@Component
public class CustomerApi {

    @Value(value = "${local.server.port}")
    private int port;

    private static final String CUSTOMER_PATH = "/api/v1/customers";

    public URI uriForCustomerId(String id) {
        return URI.create("http://localhost:" + port + CUSTOMER_PATH + "/" + id);
    }

    public WebTestClient.ResponseSpec registerCustomer(CustomerRegisterRequest request) {
        return Helper.newWebClient(port)
                .post()
                .uri(CUSTOMER_PATH)
                .bodyValue(request)
                .exchange();
    }

    public WebTestClient.ResponseSpec getCustomer(URI customerUri) {
        return Helper.newWebClient(port)
                .get()
                .uri(customerUri)
                .exchange();
    }

    public WebTestClient.ResponseSpec getCustomer(String customerId) {
        return getCustomer(uriForCustomerId(customerId));
    }

    public WebTestClient.ResponseSpec deleteCustomer(URI customerUri) {
        return Helper.newWebClient(port)
                .delete()
                .uri(customerUri)
                .exchange();
    }

    public WebTestClient.ResponseSpec deleteCustomer(String customerId) {
        return deleteCustomer(uriForCustomerId(customerId));
    }
}
