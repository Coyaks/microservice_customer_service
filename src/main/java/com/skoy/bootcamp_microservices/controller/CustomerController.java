package com.skoy.bootcamp_microservices.controller;

import com.skoy.bootcamp_microservices.dto.CustomerDto;
import com.skoy.bootcamp_microservices.service.ICustomerService;
import com.skoy.bootcamp_microservices.utils.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final ICustomerService customerService;

    @GetMapping
    public Flux<CustomerDto> getAllCustomers() {
        return customerService.FindAll();
    }

    @GetMapping("/{id}")
    public Mono<CustomerDto> getCustomerById(@PathVariable String id) {
        return customerService.findById(id);
    }

    @GetMapping("/document/{documentNumber}")
    public Mono<CustomerDto> getCustomerByDocument(@PathVariable String documentNumber) {
        return customerService.findByDocument(documentNumber);
    }

    @PostMapping
    public Mono<ApiResponse<CustomerDto>> createCustomer(@RequestBody CustomerDto customerDto) {
        return customerService.create(customerDto)
                .map(createdCustomer -> {
                    if (createdCustomer != null) {
                        return new ApiResponse<>("ok", createdCustomer, 200);
                    } else {
                        return new ApiResponse<>("error", null, 500);
                    }
                });
    }

    @PutMapping("/{id}")
    public Mono<ApiResponse<CustomerDto>> updateCustomer(@PathVariable String id, @RequestBody CustomerDto customerDto) {
        return customerService.findById(id)
                .flatMap(existingCustomer -> customerService.update(id, customerDto)
                        .map(updatedCustomer -> new ApiResponse<>("Actualizado correctamente", updatedCustomer, 200)))
                .switchIfEmpty(Mono.just(new ApiResponse<>("ID no encontrado", null, 404)));
    }

    @DeleteMapping("/{id}")
    public Mono<ApiResponse<Void>> deleteCustomer(@PathVariable String id) {
        return customerService.findById(id)
                .flatMap(existingCustomer -> customerService.delete(id)
                        .then(Mono.just(new ApiResponse<Void>("Eliminado correctamente", null, 200))))
                .switchIfEmpty(Mono.just(new ApiResponse<Void>("ID no encontrado", null, 404)))
                .onErrorResume(e -> Mono.just(new ApiResponse<Void>("Error al eliminar", null, 500)));
    }


}
