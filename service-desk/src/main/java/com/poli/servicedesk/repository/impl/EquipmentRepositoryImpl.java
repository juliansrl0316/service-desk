package com.poli.servicedesk.repository.impl;

import com.poli.servicedesk.model.Equipment;
import com.poli.servicedesk.repository.jpa.JpaEquipmentRepository;
import com.poli.servicedesk.service.EquipmentRepository;
import com.poli.servicedesk.util.ManageStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EquipmentRepositoryImpl implements EquipmentRepository {


    private final JpaEquipmentRepository jpaEquipmentRepository;
    private final ManageStatus manageStatus;

    public EquipmentRepositoryImpl(JpaEquipmentRepository jpaEquipmentRepository, ManageStatus manageStatus) {
        this.jpaEquipmentRepository = jpaEquipmentRepository;
        this.manageStatus = manageStatus;
    }

    @Override
    public Mono<Equipment> saveEquipment(Equipment equipment) {
        return Mono.justOrEmpty(jpaEquipmentRepository.save(equipment))
                .flatMap(equipmentStatus -> Mono.just(equipment)
                        .map(equipment1 -> manageStatus.setStatusName(equipmentStatus))
                        .then(Mono.just(equipmentStatus)));
    }

    @Override
    public Mono<Equipment> findById(String id) {
        return Mono.justOrEmpty(jpaEquipmentRepository.findById(id))
                .flatMap(equipment -> Mono.just(equipment)
                        .map(equipment1 -> manageStatus.setStatusName(equipment))
                        .then(Mono.just(equipment)));
    }

    @Override
    public Flux<Equipment> findAll() {
        return Flux.fromIterable(jpaEquipmentRepository.findAll())
                .flatMap(equipment -> Mono.just(equipment)
                        .map(equipment1 -> manageStatus.setStatusName(equipment))
                        .then(Mono.just(equipment)));
    }

    @Override
    public Flux<Equipment> findByStatus(String status) {
        return Flux.fromIterable(jpaEquipmentRepository.findByStatus(status))
                .flatMap(equipment -> Mono.just(equipment)
                        .map(equipment1 -> manageStatus.setStatusName(equipment))
                        .then(Mono.just(equipment)));
    }
}
