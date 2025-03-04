package com.skoy.bootcamp_microservices.dto;

import com.skoy.bootcamp_microservices.enums.CustomerTypeEnum;
import com.skoy.bootcamp_microservices.enums.DocumentTypeEnum;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private String id;
    private CustomerTypeEnum customerType;
    private String name;
    private String surname;
    private DocumentTypeEnum documentType;
    private String documentNumber;
    private String email;
    private String phone;
}
