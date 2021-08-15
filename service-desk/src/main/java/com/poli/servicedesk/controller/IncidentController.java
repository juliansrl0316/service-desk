package com.poli.servicedesk.controller;

import com.poli.servicedesk.model.Incident;
import com.poli.servicedesk.model.dto.IncidentDTO;
import com.poli.servicedesk.usecase.IncidentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/incident")
public class IncidentController {

    private final IncidentUseCase incidentUseCase;

    @PostMapping(value = "/save")
    public Mono<Incident> saveIncident(@RequestBody IncidentDTO incidentDTO) {
        return incidentUseCase.saveIncident(incidentDTO);
    }

    @GetMapping(value = "/find-by-id")
    public Mono<Incident> findById(@RequestParam(name = "id") String id) {
        return incidentUseCase.findById(id);
    }

    @GetMapping
    public Flux<Incident> findAll() {
        return incidentUseCase.findAll();
    }

    @GetMapping(value = "/find-by-status")
    public Flux<Incident> findByStatus(@RequestParam(name = "status") String status) {
        return incidentUseCase.findByStatus(status);
    }

}
