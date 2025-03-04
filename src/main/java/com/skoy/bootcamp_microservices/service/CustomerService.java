package com.skoy.bootcamp_microservices.service;

import com.skoy.bootcamp_microservices.dto.CustomerDTO;
import com.skoy.bootcamp_microservices.mapper.CustomerMapper;
import com.skoy.bootcamp_microservices.model.Customer;
import com.skoy.bootcamp_microservices.repository.ICustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CustomerService implements ICustomerService {

    private final ICustomerRepository customerRepository;

    @Override
    public Mono<CustomerDTO> create(CustomerDTO customerDto) {
        Customer customer = CustomerMapper.toEntity(customerDto);
        return customerRepository.save(customer).map(CustomerMapper::toDto);
    }

    @Override
    public Mono<CustomerDTO> findById(String id) {
        return customerRepository.findById(id).map(CustomerMapper::toDto);
    }

    @Override
    public Mono<CustomerDTO> findByDocument(String documentNumber) {
        return customerRepository.findByDocumentNumber(documentNumber).map(CustomerMapper::toDto);
    }

    @Override
    public Flux<CustomerDTO> findAll() {
        return customerRepository.findAll().map(CustomerMapper::toDto);
    }

    @Override
    public Mono<CustomerDTO> update(String id, CustomerDTO customerDto) {
        return customerRepository.findById(id)
                .flatMap(existingCustomer -> {
                    existingCustomer.setName(customerDto.getName());
                    existingCustomer.setSurname(customerDto.getSurname());
                    existingCustomer.setCustomerType(customerDto.getCustomerType());
                    existingCustomer.setDocumentType(customerDto.getDocumentType());
                    existingCustomer.setDocumentNumber(customerDto.getDocumentNumber());
                    existingCustomer.setEmail(customerDto.getEmail());
                    existingCustomer.setPhone(customerDto.getPhone());
                    existingCustomer.setUpdatedAt(LocalDateTime.now());
                    return customerRepository.save(existingCustomer);
                })
                .map(CustomerMapper::toDto);
    }

    @Override
    public Mono<Void> delete(String id) {
        return customerRepository.deleteById(id);
    }
}