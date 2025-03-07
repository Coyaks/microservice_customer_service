package com.skoy.bootcamp_microservices.service;

import com.skoy.bootcamp_microservices.dto.CustomerDTO;
import com.skoy.bootcamp_microservices.model.Customer;
import com.skoy.bootcamp_microservices.repository.ICustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

public class CustomerServiceTest {

    @Mock
    private ICustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void tearDown() {
        // Código que deseas ejecutar al final de cada prueba
        System.out.println("Test finalizado");
    }

    @Test
    public void testFindById() {
        Customer customer = new Customer();
        customer.setId("1");
        customer.setName("John");
        customer.setSurname("Doe");

        when(customerRepository.findById(anyString())).thenReturn(Mono.just(customer));

        Mono<CustomerDTO> result = customerService.findById("1");
        // StepVerifier
        StepVerifier.create(result)
                .expectNextMatches(customerDTO -> customerDTO.getName().equals("John") && customerDTO.getSurname().equals("Doe"))
                .verifyComplete();
    }

    // Prueba de eliminación de cliente
    @Test
    public void testDeleteCustomer() {
        Customer customer = new Customer();
        customer.setId("1");
        customer.setName("John");
        customer.setSurname("Doe");

        when(customerRepository.findById("1")).thenReturn(Mono.just(customer));
        when(customerRepository.deleteById("1")).thenReturn(Mono.empty());

        Mono<Void> result = customerService.delete("1");

        StepVerifier.create(result)
                .verifyComplete();
    }

    // Prueba de listar todos los clientes
    @Test
    public void testFindAllCustomers() {
        Customer customer1 = new Customer();
        customer1.setId("1");
        customer1.setName("John");
        customer1.setSurname("Doe");

        Customer customer2 = new Customer();
        customer2.setId("2");
        customer2.setName("Jane");
        customer2.setSurname("Doe");

        when(customerRepository.findAll()).thenReturn(Flux.just(customer1, customer2));

        Flux<CustomerDTO> result = customerService.findAll();

        StepVerifier.create(result)
                .expectNextMatches(dto -> dto.getName().equals("John") && dto.getSurname().equals("Doe"))
                .expectNextMatches(dto -> dto.getName().equals("Jane") && dto.getSurname().equals("Doe"))
                .verifyComplete();
    }

    @Test
    public void testCreateCustomer() {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("John");
        customerDTO.setSurname("Doe");

        Customer customer = new Customer();
        customer.setId("1");
        customer.setName("John");
        customer.setSurname("Doe");

        when(customerRepository.save(any(Customer.class))).thenReturn(Mono.just(customer));

        Mono<CustomerDTO> result = customerService.create(customerDTO);

        StepVerifier.create(result)
                .expectNextMatches(dto -> dto.getName().equals("John") && dto.getSurname().equals("Doe"))
                .verifyComplete();
    }

}