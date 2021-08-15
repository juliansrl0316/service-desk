package com.poli.servicedesk.controller;

import com.poli.servicedesk.model.Employee;
import com.poli.servicedesk.model.dto.EmployeeDTO;
import com.poli.servicedesk.usecase.EmployeeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeUseCase employeeUseCase;

    @PostMapping(value = "/save")
    public Mono<Employee> saveCustomer(@RequestBody EmployeeDTO employeeDTO) {
        return employeeUseCase.saveEmployee(employeeDTO);
    }

    @GetMapping(value = "/find-by-id")
    public Mono<Employee> findById(@RequestParam(name = "id") String id) {
        return employeeUseCase.findById(id);
    }

    @GetMapping(value = "/find-by-document")
    public Mono<Employee> findByDocument(@RequestParam(name = "document") String document) {
        return employeeUseCase.findByDocument(document);
    }

    @GetMapping
    public Flux<Employee> findAll() {
        return employeeUseCase.findAll();
    }

    @GetMapping(value = "/find-by-status")
    public Flux<Employee> findByStatus(@RequestParam(name = "status") String status) {
        return employeeUseCase.findByStatus(status);
    }

    @GetMapping(value = "/find-by-rol")
    public Flux<Employee> findByRol(@RequestParam(name = "rolId") String rolId) {
        return employeeUseCase.findByRol(rolId);
    }

}
