package com.poli.servicedesk.repository.impl;

import com.poli.servicedesk.model.Incident;
import com.poli.servicedesk.repository.jpa.JpaIncidentRepository;
import com.poli.servicedesk.service.IncidentRepository;
import com.poli.servicedesk.util.ManageStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class IncidentRepositoryImpl implements IncidentRepository {

    private final JpaIncidentRepository jpaIncidentRepository;
    private final ManageStatus manageStatus;

    public IncidentRepositoryImpl(JpaIncidentRepository jpaIncidentRepository, ManageStatus manageStatus) {
        this.jpaIncidentRepository = jpaIncidentRepository;
        this.manageStatus = manageStatus;
    }

    @Override
    public Mono<Incident> saveIncident(Incident incident) {
        return Mono.justOrEmpty(jpaIncidentRepository.save(incident))
                .flatMap(incidentStatus -> Mono.just(incident)
                        .map(incident1 -> manageStatus.setStatusName(incidentStatus))
                        .then(Mono.just(incidentStatus)));
    }

    @Override
    public Mono<Incident> findById(String id) {
        return Mono.justOrEmpty(jpaIncidentRepository.findById(id))
                .flatMap(incident -> Mono.just(incident)
                        .map(incident1 -> manageStatus.setStatusName(incident))
                        .then(Mono.just(incident)));
    }

    @Override
    public Flux<Incident> findAll() {
        return Flux.fromIterable(jpaIncidentRepository.findAll())
                .flatMap(incident -> Mono.just(incident)
                        .map(incident1 -> manageStatus.setStatusName(incident))
                        .then(Mono.just(incident)));
    }

    @Override
    public Flux<Incident> findByStatus(String status) {
        return Flux.fromIterable(jpaIncidentRepository.findByStatus(status))
                .flatMap(incident -> Mono.just(incident)
                        .map(incident1 -> manageStatus.setStatusName(incident))
                        .then(Mono.just(incident)));
    }


}
