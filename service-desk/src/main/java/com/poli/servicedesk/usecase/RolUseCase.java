package com.poli.servicedesk.usecase;

import com.poli.servicedesk.model.Rol;
import com.poli.servicedesk.model.dto.RolDTO;
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
public class RolUseCase {

    private final RolRepository rolRepository;

    public Mono<Rol> saveRol(RolDTO rolDTO) {
        log.info("=== RolUseCase:::saveRol {}", rolDTO);
        return rolRepository.findById(rolDTO.getId())
                .map(rolToUpdate -> rolToUpdate.toBuilder()
                        .name(rolDTO.getName())
                        .description(rolDTO.getDescription())
                        .status(rolDTO.getStatus())
                        .build())
                .defaultIfEmpty(Rol.builder()
                        .id(UUID.randomUUID().toString())
                        .name(rolDTO.getName())
                        .description(rolDTO.getDescription())
                        .status(rolDTO.getStatus())
                        .build())
                .flatMap(rolRepository::saveRol);
    }

    public Flux<Rol> findAll(){
        log.info("=== RolUseCase:::findAll");
        return rolRepository.findAll();
    }

}
