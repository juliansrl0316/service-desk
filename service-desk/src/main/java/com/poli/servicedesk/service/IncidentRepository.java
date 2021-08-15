package com.poli.servicedesk.service;

import com.poli.servicedesk.model.Incident;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IncidentRepository {

    Mono<Incident> saveIncident(Incident incident);
    Mono<Incident> findById(String id);
    Flux<Incident> findAll();
    Flux<Incident> findByStatus(String status);

}
