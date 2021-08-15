package com.poli.servicedesk.usecase;

import com.poli.servicedesk.enums.StatusEnum;
import com.poli.servicedesk.model.IncidentType;
import com.poli.servicedesk.model.dto.IncidentTypeDTO;
import com.poli.servicedesk.service.IncidentTypeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class IncidentTypeUseCase {

    private final IncidentTypeRepository incidentTypeRepository;

    public Mono<IncidentType> saveIncidentType(IncidentTypeDTO incidentTypeDTO) {
        log.info("=== IncidentTypeUseCase:::IncidentType {}", incidentTypeDTO);
        return incidentTypeRepository.findById(incidentTypeDTO.getId())
                .map(rolToUpdate -> rolToUpdate.toBuilder()
                        .name(incidentTypeDTO.getName())
                        .description(incidentTypeDTO.getDescription())
                        .status(StatusEnum.idFromName(incidentTypeDTO.getStatus()))
                        .build())
                .defaultIfEmpty(IncidentType.builder()
                        .id(UUID.randomUUID().toString())
                        .name(incidentTypeDTO.getName())
                        .description(incidentTypeDTO.getDescription())
                        .status(StatusEnum.idFromName(incidentTypeDTO.getStatus()))
                        .build())
                .flatMap(incidentTypeRepository::saveIncidentType);
    }

    public Flux<IncidentType> findAll(){
        log.info("=== IncidentTypeUseCase:::findAll");
        return incidentTypeRepository.findAll();
    }

}
