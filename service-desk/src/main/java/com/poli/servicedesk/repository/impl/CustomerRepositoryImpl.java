package com.poli.servicedesk.repository.impl;

import com.poli.servicedesk.model.Customer;
import com.poli.servicedesk.repository.jpa.JpaCustomerRepository;
import com.poli.servicedesk.service.CustomerRepository;
import com.poli.servicedesk.util.ManageStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerRepositoryImpl implements CustomerRepository {

    private final JpaCustomerRepository jpaCustomerRepository;
    private final ManageStatus manageStatus;

    public CustomerRepositoryImpl(JpaCustomerRepository jpaCustomerRepository, ManageStatus manageStatus) {
        this.jpaCustomerRepository = jpaCustomerRepository;
        this.manageStatus = manageStatus;
    }

    @Override
    public Mono<Customer> saveCustomer(Customer customer) {
        return Mono.justOrEmpty(jpaCustomerRepository.save(customer))
                .flatMap(customerStatus -> Mono.just(customer)
                        .map(customer1 -> manageStatus.setStatusName(customerStatus))
                        .then(Mono.just(customerStatus)));
    }

    @Override
    public Mono<Customer> findById(String id) {
        return Mono.justOrEmpty(jpaCustomerRepository.findById(id))
                .flatMap(customer -> Mono.just(customer)
                        .map(customer1 -> manageStatus.setStatusName(customer))
                        .then(Mono.just(customer)));
    }

    @Override
    public Mono<Customer> findByDocument(String document) {
        return Mono.justOrEmpty(jpaCustomerRepository.findByDocumentNumber(document))
                .flatMap(customer -> Mono.just(customer)
                        .map(customer1 -> manageStatus.setStatusName(customer))
                        .then(Mono.just(customer)));
    }

    @Override
    public Flux<Customer> findAll() {
        return Flux.fromIterable(jpaCustomerRepository.findAll())
                .flatMap(customer -> Mono.just(customer)
                        .map(customer1 -> manageStatus.setStatusName(customer))
                        .then(Mono.just(customer)));
    }

    @Override
    public Flux<Customer> findByStatus(String status) {
        return Flux.fromIterable(jpaCustomerRepository.findByStatus(status))
                .flatMap(customer -> Mono.just(customer)
                        .map(customer1 -> manageStatus.setStatusName(customer))
                        .then(Mono.just(customer)));
    }
}
