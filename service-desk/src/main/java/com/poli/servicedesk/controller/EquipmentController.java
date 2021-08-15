package com.poli.servicedesk.controller;

import com.poli.servicedesk.model.Equipment;
import com.poli.servicedesk.model.dto.EquipmentDTO;
import com.poli.servicedesk.usecase.EquipmentUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/equipment")
public class EquipmentController {

    private final EquipmentUseCase equipmentUseCase;

    @PostMapping(value = "/save")
    public Mono<Equipment> saveEquipment(@RequestBody EquipmentDTO equipmentDTO) {
        return equipmentUseCase.saveEquipment(equipmentDTO);
    }

    @GetMapping(value = "/find-by-id")
    public Mono<Equipment> findById(@RequestParam(name = "id") String id) {
        return equipmentUseCase.findById(id);
    }

    @GetMapping
    public Flux<Equipment> findAll() {
        return equipmentUseCase.findAll();
    }

    @GetMapping(value = "/find-by-status")
    public Flux<Equipment> findByStatus(@RequestParam(name = "status") String status) {
        return equipmentUseCase.findByStatus(status);
    }

}
