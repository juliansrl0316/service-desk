package com.poli.servicedesk.controller;

import com.poli.servicedesk.model.Customer;
import com.poli.servicedesk.model.dto.CustomerDTO;
import com.poli.servicedesk.usecase.CustomerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/customer")
public class CustomerController {

    private final CustomerUseCase customerUseCase;

    @PostMapping(value = "/save")
    public Mono<Customer> saveCustomer(@RequestBody CustomerDTO customerDTO) {
        return customerUseCase.saveCustomer(customerDTO);
    }

    @GetMapping(value = "/find-by-id")
    public Mono<Customer> findById(@RequestParam(name = "id") String id) {
        return customerUseCase.findById(id);
    }

    @GetMapping(value = "/find-by-document")
    public Mono<Customer> findByDocument(@RequestParam(name = "document") String document) {
        return customerUseCase.findByDocument(document);
    }

    @GetMapping
    public Flux<Customer> findAll() {
        return customerUseCase.findAll();
    }

    @GetMapping(value = "/find-by-status")
    public Flux<Customer> findByStatus(@RequestParam(name = "status") String status) {
        return customerUseCase.findByStatus(status);
    }

}
