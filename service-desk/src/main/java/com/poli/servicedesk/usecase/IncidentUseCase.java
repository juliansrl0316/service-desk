package com.poli.servicedesk.usecase;

import com.poli.servicedesk.enums.ServiceDeskErrorEnum;
import com.poli.servicedesk.enums.StatusEnum;
import com.poli.servicedesk.exception.ServiceDeskException;
import com.poli.servicedesk.model.Incident;
import com.poli.servicedesk.model.dto.IncidentDTO;
import com.poli.servicedesk.service.CustomerRepository;
import com.poli.servicedesk.service.EmployeeRepository;
import com.poli.servicedesk.service.IncidentRepository;
import com.poli.servicedesk.service.IncidentTypeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class IncidentUseCase {

    private final IncidentRepository incidentRepository;
    private final IncidentTypeRepository incidentTypeRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;

    public Mono<Incident> saveIncident(IncidentDTO incidentDTO) {
        log.info("=== IncidentUseCase:::saveCustomer {}", incidentDTO);
        return incidentTypeRepository.findById(incidentDTO.getIncidentTypeId())
                .switchIfEmpty(Mono.defer(() -> Mono.error(new ServiceDeskException(ServiceDeskErrorEnum.INCIDENT_TYPE_NOT_REGISTERED))))
                .flatMap(incidentType -> employeeRepository.findById(incidentDTO.getEmployeeId()))
                .switchIfEmpty(Mono.defer(() -> Mono.error(new ServiceDeskException(ServiceDeskErrorEnum.EMPLOYEE_NOT_REGISTERED))))
                .flatMap(employee -> customerRepository.findById(incidentDTO.getCustomerId()))
                .switchIfEmpty(Mono.defer(() -> Mono.error(new ServiceDeskException(ServiceDeskErrorEnum.CUSTOMER_NOT_REGISTERED))))
                .flatMap(customer -> incidentRepository.findById(incidentDTO.getId())
                        .map(incidentToEdit -> incidentToEdit.toBuilder()
                                .registerDate(new Date())
                                .status(StatusEnum.idFromName(incidentDTO.getStatus()))
                                .incidentTypeId(incidentDTO.getIncidentTypeId())
                                .employeeId(incidentDTO.getEmployeeId())
                                .customerId(incidentDTO.getCustomerId())
                                .build())
                        .defaultIfEmpty(Incident.builder()
                                .id(UUID.randomUUID().toString())
                                .registerDate(new Date())
                                .status(StatusEnum.idFromName(incidentDTO.getStatus()))
                                .incidentTypeId(incidentDTO.getIncidentTypeId())
                                .employeeId(incidentDTO.getEmployeeId())
                                .customerId(incidentDTO.getCustomerId())
                                .build()))
                .flatMap(incidentRepository::saveIncident);

    }

    public Mono<Incident> findById(String id) {
        log.info("=== IncidentUseCase:::findById {}", id);
        return incidentRepository.findById(id);
    }

    public Flux<Incident> findAll() {
        log.info("=== IncidentUseCase:::findAll");
        return incidentRepository.findAll();
    }

    public Flux<Incident> findByStatus(String status) {
        log.info("=== IncidentUseCase:::findByStatus");
        return Flux.just(StatusEnum.idFromName(status))
                .flatMap(incidentRepository::findByStatus);
    }

}
