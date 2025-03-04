package com.skoy.bootcamp_microservices.mapper;

import com.skoy.bootcamp_microservices.dto.CustomerDTO;
import com.skoy.bootcamp_microservices.model.Customer;

import java.time.LocalDateTime;

public class CustomerMapper {

    public static CustomerDTO toDto(Customer customer) {
        return new CustomerDTO(
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

    public static Customer toEntity(CustomerDTO dto) {
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