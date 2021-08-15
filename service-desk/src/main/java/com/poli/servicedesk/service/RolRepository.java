package com.poli.servicedesk.service;

import com.poli.servicedesk.model.Rol;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RolRepository {

    Mono<Rol> findById(String id);
    Mono<Rol> saveRol(Rol rol);
    Flux<Rol> findAll();

}
