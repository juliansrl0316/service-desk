package com.poli.servicedesk.controller;

import com.poli.servicedesk.model.IncidentType;
import com.poli.servicedesk.model.dto.IncidentTypeDTO;
import com.poli.servicedesk.usecase.IncidentTypeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/incident-type")
public class IncidentTypeController {

    private final IncidentTypeUseCase incidentTypeUseCase;

    @PostMapping(value = "/save")
    public Mono<IncidentType> saveIncidentType(@RequestBody IncidentTypeDTO incidentTypeDTO) {
        return incidentTypeUseCase.saveIncidentType(incidentTypeDTO);
    }

    @GetMapping
    public Flux<IncidentType> findAll() {
        return incidentTypeUseCase.findAll();
    }

}
