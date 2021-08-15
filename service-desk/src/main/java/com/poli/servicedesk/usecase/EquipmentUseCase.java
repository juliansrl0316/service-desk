package com.poli.servicedesk.usecase;

import com.poli.servicedesk.enums.ServiceDeskErrorEnum;
import com.poli.servicedesk.enums.StatusEnum;
import com.poli.servicedesk.exception.ServiceDeskException;
import com.poli.servicedesk.model.Equipment;
import com.poli.servicedesk.model.dto.EquipmentDTO;
import com.poli.servicedesk.service.CustomerRepository;
import com.poli.servicedesk.service.EquipmentRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class EquipmentUseCase {

    private final EquipmentRepository equipmentRepository;
    private final CustomerRepository customerRepository;

    public Mono<Equipment> saveEquipment(EquipmentDTO equipmentDTO) {
        log.info("=== EquipmentUseCase:::saveEquipment {}", equipmentDTO);
        return equipmentRepository.findById(equipmentDTO.getId())
                .map(equipmentToUpdate -> equipmentToUpdate.toBuilder()
                        .motherBoard(equipmentDTO.getMotherBoard())
                        .ramCapacity(equipmentDTO.getRamCapacity())
                        .storage(equipmentDTO.getStorage())
                        .processor(equipmentDTO.getProcessor())
                        .operatingSystem(equipmentDTO.getOperatingSystem())
                        .status(StatusEnum.idFromName(equipmentDTO.getStatus()))
                        .build())
                .switchIfEmpty(Mono.defer(() -> customerRepository.findById(equipmentDTO.getCustomerId())
                        .map(customer -> Equipment.builder()
                                .id(UUID.randomUUID().toString())
                                .motherBoard(equipmentDTO.getMotherBoard())
                                .ramCapacity(equipmentDTO.getRamCapacity())
                                .storage(equipmentDTO.getStorage())
                                .processor(equipmentDTO.getProcessor())
                                .operatingSystem(equipmentDTO.getOperatingSystem())
                                .status(StatusEnum.idFromName(equipmentDTO.getStatus()))
                                .customerId(equipmentDTO.getCustomerId())
                                .build())
                        .switchIfEmpty(Mono.defer(() -> Mono.error(new ServiceDeskException(ServiceDeskErrorEnum.CUSTOMER_NOT_REGISTERED))))))
                .flatMap(equipmentRepository::saveEquipment);
    }

    public Mono<Equipment> findById(String id) {
        log.info("=== EquipmentUseCase:::findById {}", id);
        return equipmentRepository.findById(id);
    }

    public Flux<Equipment> findAll() {
        log.info("=== EquipmentUseCase:::findAll");
        return equipmentRepository.findAll();
    }

    public Flux<Equipment> findByStatus(String status) {
        log.info("=== EquipmentUseCase:::findByStatus");
        return Flux.just(StatusEnum.idFromName(status))
                .flatMap(equipmentRepository::findByStatus);
    }


}
