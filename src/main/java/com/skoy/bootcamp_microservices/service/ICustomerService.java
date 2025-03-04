package com.skoy.bootcamp_microservices.service;

import com.skoy.bootcamp_microservices.dto.CustomerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICustomerService {

    Flux<CustomerDTO> findAll();
    Mono<CustomerDTO> findById(String id);
    Mono<CustomerDTO> findByDocument(String documentNumber);
    Mono<CustomerDTO> create(CustomerDTO customerDto);
    Mono<CustomerDTO> update(String id, CustomerDTO customerDto);
    Mono<Void> delete(String id);
}
