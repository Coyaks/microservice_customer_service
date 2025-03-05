package com.skoy.bootcamp_microservices.controller;

import com.skoy.bootcamp_microservices.dto.CustomerDTO;
import com.skoy.bootcamp_microservices.enums.CustomerTypeEnum;
import com.skoy.bootcamp_microservices.enums.DocumentTypeEnum;
import com.skoy.bootcamp_microservices.service.ICustomerService;
import com.skoy.bootcamp_microservices.utils.ApiResponse;
import com.skoy.bootcamp_microservices.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private ICustomerService customerService;

    @GetMapping
    public Flux<CustomerDTO> findAll() {
        logger.info("Fetching all customers");
        return customerService.findAll()
                .doOnNext(customer -> logger.info("Customer found: {}", customer))
                .doOnComplete(() -> logger.info("All customers fetched successfully."));
    }

    @GetMapping("/{id}")
    public Mono<ApiResponse<CustomerDTO>> findById(@PathVariable String id) {
        logger.info("Fetching customer with ID: {}", id);
        return customerService.findById(id)
                .map(customer -> {
                    logger.info("Customer found: {}", customer);
                    return new ApiResponse<>("Cliente encontrado", customer, Constants.STATUS_OK);
                })
                .switchIfEmpty(Mono.just(new ApiResponse<>("Cliente no encontrado", null, Constants.STATUS_E404)))
                .doOnError(e -> logger.error("Error fetching customer with ID: {}", id, e));
    }

    @GetMapping("/document/{documentNumber}")
    public Mono<CustomerDTO> findByDocument(@PathVariable String documentNumber) {
        logger.info("Fetching customer with document number: {}", documentNumber);
        return customerService.findByDocument(documentNumber)
                .doOnNext(customer -> logger.info("Customer found: {}", customer))
                .doOnError(e -> logger.error("Error fetching customer with document number: {}", documentNumber, e));
    }

    @PostMapping
    public Mono<ApiResponse<CustomerDTO>> create(@RequestBody CustomerDTO customerDto) {
        logger.info("Creating new customer: {}", customerDto);
        return customerService.create(customerDto)
                .map(createdCustomer -> {
                    if (createdCustomer != null) {
                        logger.info("Customer created successfully: {}", createdCustomer);
                        return new ApiResponse<>("ok", createdCustomer, Constants.STATUS_OK);
                    } else {
                        logger.error("Error creating customer");
                        return new ApiResponse<>("error", null, Constants.STATUS_E500);
                    }
                });
    }

    @PutMapping("/{id}")
    public Mono<ApiResponse<CustomerDTO>> update(@PathVariable String id, @RequestBody CustomerDTO customerDto) {
        logger.info("Updating customer with ID: {}", id);
        return customerService.findById(id)
                .flatMap(existingCustomer -> customerService.update(id, customerDto)
                        .map(updatedCustomer -> {
                            logger.info("Customer updated successfully: {}", updatedCustomer);
                            return new ApiResponse<>("Actualizado correctamente", updatedCustomer, Constants.STATUS_OK);
                        }))
                .switchIfEmpty(Mono.just(new ApiResponse<>("ID no encontrado", null, Constants.STATUS_E404)))
                .doOnError(e -> logger.error("Error updating customer with ID: {}", id, e));
    }

    @DeleteMapping("/{id}")
    public Mono<ApiResponse<Void>> delete(@PathVariable String id) {
        logger.info("Deleting customer with ID: {}", id);
        return customerService.findById(id)
                .flatMap(existingCustomer -> customerService.delete(id)
                        .then(Mono.just(new ApiResponse<Void>("Eliminado correctamente", null, Constants.STATUS_OK))))
                .switchIfEmpty(Mono.just(new ApiResponse<Void>("ID no encontrado", null, Constants.STATUS_E404)))
                .onErrorResume(e -> {
                    logger.error("Error deleting customer with ID: {}", id, e);
                    return Mono.just(new ApiResponse<Void>("Error al eliminar", null, Constants.STATUS_E500));
                });
    }

    @GetMapping("/types")
    public CustomerTypeEnum[] getAllCustomerTypes() {
        return CustomerTypeEnum.values();
    }

    @GetMapping("/document_types")
    public DocumentTypeEnum[] getAllDocumentTypes() {
        return DocumentTypeEnum.values();
    }

}
