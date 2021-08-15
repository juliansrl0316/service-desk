package com.poli.servicedesk.service;

import com.poli.servicedesk.model.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeRepository {

    Mono<Employee> saveEmployee(Employee employee);
    Mono<Employee> findById(String id);
    Mono<Employee> findByDocument(String document);
    Flux<Employee> findAll();
    Flux<Employee> findByStatus(String status);
    Flux<Employee> findByRol(String rolId);


}
