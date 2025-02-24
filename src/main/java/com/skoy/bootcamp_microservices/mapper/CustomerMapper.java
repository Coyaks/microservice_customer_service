package com.skoy.bootcamp_microservices.mapper;

import com.skoy.bootcamp_microservices.dto.CustomerDto;
import com.skoy.bootcamp_microservices.model.Customer;

import java.time.LocalDateTime;

public class CustomerMapper {

    public static CustomerDto toDto(Customer customer) {
        return new CustomerDto(
                customer.getId(),
                customer.getCustomerType(),
                customer.getName(),
                customer.getSurname(),
                customer.getDocumentType(),
                customer.getDocumentNumber(),
                customer.getEmail(),
                customer.getPhone()
        );
    }

    public static Customer toEntity(CustomerDto dto) {
        return new Customer(
                dto.getId(),
                dto.getCustomerType(),
                dto.getName(),
                dto.getSurname(),
                dto.getDocumentType(),
                dto.getDocumentNumber(),
                dto.getEmail(),
                dto.getPhone(),
                LocalDateTime.now(),
                LocalDateTime.now());
    }
}