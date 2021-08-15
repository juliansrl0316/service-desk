package com.poli.servicedesk.service;

import com.poli.servicedesk.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerRepository {

    Mono<Customer> saveCustomer(Customer customer);
    Mono<Customer> findById(String id);
    Mono<Customer> findByDocument(String document);
    Flux<Customer> findAll();
    Flux<Customer> findByStatus(String status);
}
