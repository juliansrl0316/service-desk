package com.poli.servicedesk.service;

import com.poli.servicedesk.model.Equipment;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EquipmentRepository {

    Mono<Equipment> saveEquipment(Equipment equipment);
    Mono<Equipment> findById(String id);
    Flux<Equipment> findAll();
    Flux<Equipment> findByStatus(String status);

}
