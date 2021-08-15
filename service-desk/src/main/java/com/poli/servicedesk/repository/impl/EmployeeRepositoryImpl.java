package com.poli.servicedesk.repository.impl;

import com.poli.servicedesk.model.Employee;
import com.poli.servicedesk.repository.jpa.JpaEmployeeRepository;
import com.poli.servicedesk.service.EmployeeRepository;
import com.poli.servicedesk.util.ManageStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final JpaEmployeeRepository jpaEmployeeRepository;
    private final ManageStatus manageStatus;

    public EmployeeRepositoryImpl(JpaEmployeeRepository jpaEmployeeRepository, ManageStatus manageStatus) {
        this.jpaEmployeeRepository = jpaEmployeeRepository;
        this.manageStatus = manageStatus;
    }

    @Override
    public Mono<Employee> saveEmployee(Employee employee) {
        return Mono.justOrEmpty(jpaEmployeeRepository.save(employee))
                .flatMap(employeeStatus -> Mono.just(employee)
                        .map(employee1 -> manageStatus.setStatusName(employeeStatus))
                        .then(Mono.just(employeeStatus)))
                .map(manageStatus::manageRol);
    }

    @Override
    public Mono<Employee> findById(String id) {
        return Mono.justOrEmpty(jpaEmployeeRepository.findById(id))
                .flatMap(employee -> Mono.just(employee)
                        .map(employee1 -> manageStatus.setStatusName(employee))
                        .then(Mono.just(employee)))
                .map(manageStatus::manageRol);
    }

    @Override
    public Mono<Employee> findByDocument(String document) {
        return Mono.justOrEmpty(jpaEmployeeRepository.findByDocumentNumber(document))
                .flatMap(employee -> Mono.just(employee)
                        .map(employee1 -> manageStatus.setStatusName(employee))
                        .then(Mono.just(employee)))
                .map(manageStatus::manageRol);
    }

    @Override
    public Flux<Employee> findAll() {
        return Flux.fromIterable(jpaEmployeeRepository.findAll())
                .flatMap(employee -> Mono.just(employee)
                        .map(employee1 -> manageStatus.setStatusName(employee))
                        .then(Mono.just(employee)))
                .map(manageStatus::manageRol);
    }

    @Override
    public Flux<Employee> findByStatus(String status) {
        return Flux.fromIterable(jpaEmployeeRepository.findByStatus(status))
                .flatMap(employee -> Mono.just(employee)
                        .map(employee1 -> manageStatus.setStatusName(employee))
                        .then(Mono.just(employee)))
                .map(manageStatus::manageRol);
    }

    @Override
    public Flux<Employee> findByRol(String rolId) {
        return Flux.fromIterable(jpaEmployeeRepository.findByRolId(rolId))
                .flatMap(employee -> Mono.just(employee)
                        .map(employee1 -> manageStatus.setStatusName(employee))
                        .then(Mono.just(employee)))
                .map(manageStatus::manageRol);
    }

}
