package com.skoy.bootcamp_microservices.service;

import com.skoy.bootcamp_microservices.dto.CustomerDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICustomerService {

    Flux<CustomerDto> FindAll();
    Mono<CustomerDto> findById(String id);
    Mono<CustomerDto> findByDocument(String documentNumber);
    Mono<CustomerDto> create(CustomerDto customerDto);
    Mono<CustomerDto> update(String id, CustomerDto customerDto);
    Mono<Void> delete(String id);
}
