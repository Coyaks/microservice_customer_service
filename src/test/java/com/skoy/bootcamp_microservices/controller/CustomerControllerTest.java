package com.skoy.bootcamp_microservices.controller;

import com.skoy.bootcamp_microservices.dto.CustomerDTO;
import com.skoy.bootcamp_microservices.service.ICustomerService;
import com.skoy.bootcamp_microservices.utils.ApiResponse;
import com.skoy.bootcamp_microservices.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CustomerControllerTest {

    @Mock
    private ICustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private WebTestClient webTestClient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        webTestClient = WebTestClient.bindToController(customerController).build();
    }

    @Test
    public void testFindById() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("John");
        customerDTO.setSurname("Doe");

        when(customerService.findById(anyString())).thenReturn(Mono.just(customerDTO));

        webTestClient.get().uri("/api/v1/customers/{id}", "1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(ApiResponse.class)
                .value(response -> {
                    ApiResponse<CustomerDTO> apiResponse = (ApiResponse<CustomerDTO>) response;
                    assert apiResponse.getStatusCode() == Constants.STATUS_OK;
                    assert apiResponse.getData().getName().equals("John");
                    assert apiResponse.getData().getSurname().equals("Doe");
                });
    }
}