package com.poli.servicedesk.repository.impl;

import com.poli.servicedesk.model.Rol;
import com.poli.servicedesk.repository.jpa.JpaRolRepository;
import com.poli.servicedesk.service.RolRepository;
import com.poli.servicedesk.util.ManageStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RolRepositoryImpl implements RolRepository {

    private final JpaRolRepository jpaRolRepository;
    private final ManageStatus manageStatus;

    public RolRepositoryImpl(JpaRolRepository jpaRolRepository, ManageStatus manageStatus) {
        this.jpaRolRepository = jpaRolRepository;
        this.manageStatus = manageStatus;
    }

    @Override
    public Mono<Rol> findById(String id) {
        return Mono.justOrEmpty(jpaRolRepository.findById(id))
                .flatMap(rolStatus -> Mono.just(rolStatus)
                        .map(rol -> manageStatus.setStatusName(rolStatus))
                        .then(Mono.just(rolStatus)));
    }

    @Override
    public Mono<Rol> saveRol(Rol rol) {
        return Mono.justOrEmpty(jpaRolRepository.save(rol))
                .flatMap(rolStatus -> Mono.just(rol)
                        .map(rol1 -> manageStatus.setStatusName(rolStatus))
                        .then(Mono.just(rolStatus)));
    }

    @Override
    public Flux<Rol> findAll() {
        return Flux.fromIterable(jpaRolRepository.findAll())
                .flatMap(rolStatus -> Mono.just(rolStatus)
                        .map(rol -> manageStatus.setStatusName(rolStatus))
                        .then(Mono.just(rolStatus)));
    }
}
