package com.poli.servicedesk.service;

import com.poli.servicedesk.model.IncidentType;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IncidentTypeRepository {

    Mono<IncidentType> findById(String id);
    Mono<IncidentType> saveIncidentType(IncidentType incidentType);
    Flux<IncidentType> findAll();

}
