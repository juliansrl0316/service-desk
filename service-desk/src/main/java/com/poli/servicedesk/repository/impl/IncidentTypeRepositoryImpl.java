package com.poli.servicedesk.repository.impl;

import com.poli.servicedesk.model.IncidentType;
import com.poli.servicedesk.repository.jpa.JpaIncidentTypeRepository;
import com.poli.servicedesk.service.IncidentTypeRepository;
import com.poli.servicedesk.util.ManageStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class IncidentTypeRepositoryImpl implements IncidentTypeRepository {

    private final JpaIncidentTypeRepository jpaIncidentTypeRepository;
    private final ManageStatus manageStatus;

    public IncidentTypeRepositoryImpl(JpaIncidentTypeRepository jpaIncidentTypeRepository, ManageStatus manageStatus) {
        this.jpaIncidentTypeRepository = jpaIncidentTypeRepository;
        this.manageStatus = manageStatus;
    }

    @Override
    public Mono<IncidentType> findById(String id) {
        return Mono.justOrEmpty(jpaIncidentTypeRepository.findById(id))
                .flatMap(rolStatus -> Mono.just(rolStatus)
                        .map(incidentType -> manageStatus.setStatusName(rolStatus))
                        .then(Mono.just(rolStatus)));
    }

    @Override
    public Mono<IncidentType> saveIncidentType(IncidentType incidentType) {
        return Mono.justOrEmpty(jpaIncidentTypeRepository.save(incidentType))
                .flatMap(incidentTypeStatus -> Mono.just(incidentType)
                        .map(incidentType1 -> manageStatus.setStatusName(incidentTypeStatus))
                        .then(Mono.just(incidentTypeStatus)));
    }

    @Override
    public Flux<IncidentType> findAll() {
        return Flux.fromIterable(jpaIncidentTypeRepository.findAll())
                .flatMap(incidentTypeStatus -> Mono.just(incidentTypeStatus)
                        .map(incidentType -> manageStatus.setStatusName(incidentTypeStatus))
                        .then(Mono.just(incidentTypeStatus)));
    }

}
