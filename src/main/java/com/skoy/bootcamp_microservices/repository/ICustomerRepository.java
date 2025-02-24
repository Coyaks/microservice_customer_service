package com.skoy.bootcamp_microservices.repository;

import com.skoy.bootcamp_microservices.model.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ICustomerRepository extends ReactiveMongoRepository<Customer, String> {
    Mono<Customer> findByDocumentNumber(String documentNumber);
}
