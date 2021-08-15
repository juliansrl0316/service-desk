package com.poli.servicedesk.controller;

import com.poli.servicedesk.model.Rol;
import com.poli.servicedesk.model.dto.RolDTO;
import com.poli.servicedesk.usecase.RolUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/rol")
public class RolController {

    private final RolUseCase rolUseCase;

    @PostMapping(value = "/save")
    public Mono<Rol> saveRol(@RequestBody RolDTO rolDTO) {
        return rolUseCase.saveRol(rolDTO);
    }

    @GetMapping
    public Flux<Rol> findAll() {
        return rolUseCase.findAll();
    }

}
