package org.development.customerservice.customer;

import org.development.customerservice.Helper;
import org.development.customerservice.handler.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.net.URI;
import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
        "spring.cloud.config.enabled=false"
        , "eureka.client.enabled=false"
})
@Testcontainers
public class CustomerTests {

    @LocalServerPort
    private int port;

    @Container
    @ServiceConnection
    private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.4");

    @Autowired
    CustomerApi customerApi;

    @Test
    public void givenIAmAdmin_WhenCheckingCustomerThatNotExists_ThenReturnNotFound() {
        WebTestClient.ResponseSpec response =
                customerApi.getCustomer(UUID.randomUUID().toString())
                        // test
                        .expectStatus().isNotFound();
    }

    @Test
    public void givenIAmAdmin_WhenDeletingCustomerThatNotExists_ThenReturnNotFound() {
        WebTestClient.ResponseSpec response =
                customerApi.deleteCustomer(UUID.randomUUID().toString())
                        // test
                        .expectStatus().isNotFound();
    }

    @Test
    public void givenIAmNewCustomer_WhenIRegisterWithValidData() {
        CustomerRegisterRequest request = new CustomerRegisterRequest("Test", "Test", "email@test.com", null);

        WebTestClient.ResponseSpec response =
                customerApi.registerCustomer(request);
        // test TODO
        response.expectStatus().isCreated();
    }


    @Test
    public void givenIAmNewCustomer_WhenIRegisterWithInvalidData_ThenReturnBadRequest() {
        CustomerRegisterRequest request = new CustomerRegisterRequest(null, "", "email@.com", null);
        WebTestClient.ResponseSpec response =
                customerApi.registerCustomer(request);
        // test TODO
        response.expectStatus().isBadRequest();
        System.out.println(response.expectBody(ErrorResponse.class).returnResult().getResponseBody());
    }

    @Test
    public void givenIHaveRegister_WhenICheckMyDetails() {
        CustomerRegisterRequest request = new CustomerRegisterRequest("Test", "Test", "email@test.com", null);

        WebTestClient.ResponseSpec response = customerApi
                .registerCustomer(request);
        CustomerResponse newCustomer = response
                .expectBody(CustomerResponse.class)
                .returnResult()
                .getResponseBody();

        URI newCustomerLocation = response
                .expectBody(CustomerResponse.class)
                .returnResult()
                .getResponseHeaders().getLocation();

        // test
        customerApi.getCustomer(newCustomerLocation).expectStatus().isOk();
    }
}
