package com.poli.servicedesk.usecase;

import com.poli.servicedesk.enums.RolEnum;
import com.poli.servicedesk.enums.ServiceDeskErrorEnum;
import com.poli.servicedesk.enums.StatusEnum;
import com.poli.servicedesk.exception.ServiceDeskException;
import com.poli.servicedesk.model.Employee;
import com.poli.servicedesk.model.dto.EmployeeDTO;
import com.poli.servicedesk.service.EmployeeRepository;
import com.poli.servicedesk.service.RolRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class EmployeeUseCase {

    private final EmployeeRepository employeeRepository;
    private final RolRepository rolRepository;

    public Mono<Employee> saveEmployee(EmployeeDTO employeeDTO) {
        log.info("=== EmployeeUseCase:::saveCustomer {}", employeeDTO);
        return rolRepository.findById(employeeDTO.getRolId())
                .switchIfEmpty(Mono.defer(() -> Mono.error(new ServiceDeskException(ServiceDeskErrorEnum.ROL_NOT_REGISTERED))))
                .flatMap(rol -> employeeRepository.findById(employeeDTO.getId())
                        .map(employeeToUpdate -> employeeToUpdate.toBuilder()
                                .name(employeeDTO.getName())
                                .firstName(employeeDTO.getFirstName())
                                .secondName(employeeDTO.getSecondName())
                                .status(StatusEnum.idFromName(employeeDTO.getStatus()))
                                .build())
                        .defaultIfEmpty(Employee.builder()
                                .id(UUID.randomUUID().toString())
                                .name(employeeDTO.getName())
                                .firstName(employeeDTO.getFirstName())
                                .secondName(employeeDTO.getSecondName())
                                .documentNumber(employeeDTO.getDocumentNumber())
                                .status(StatusEnum.idFromName(employeeDTO.getStatus()))
                                .rolId(employeeDTO.getRolId())
                                .build()))
                .flatMap(employeeRepository::saveEmployee);

    }

    public Mono<Employee> findById(String id) {
        log.info("=== EmployeeUseCase:::findById {}", id);
        return employeeRepository.findById(id);
    }

    public Mono<Employee> findByDocument(String document) {
        log.info("=== EmployeeUseCase:::findByDocument {}", document);
        return employeeRepository.findByDocument(document);
    }

    public Flux<Employee> findAll() {
        log.info("=== EmployeeUseCase:::findAll");
        return employeeRepository.findAll();
    }

    public Flux<Employee> findByStatus(String status) {
        log.info("=== EmployeeUseCase:::findByStatus");
        return Flux.just(StatusEnum.idFromName(status))
                .flatMap(employeeRepository::findByStatus);
    }

    public Flux<Employee> findByRol(String rolId) {
        log.info("=== EmployeeUseCase:::findByRol");
        return Flux.just(RolEnum.idFromName(rolId))
                .flatMap(employeeRepository::findByRol);
    }
}
