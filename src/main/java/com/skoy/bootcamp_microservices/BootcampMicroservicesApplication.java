package com.skoy.bootcamp_microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
@SpringBootApplication(scanBasePackages = "com.skoy.bootcamp_microservices")
public class BootcampMicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootcampMicroservicesApplication.class, args);
	}

}
