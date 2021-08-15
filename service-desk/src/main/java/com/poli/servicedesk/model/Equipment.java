package com.poli.servicedesk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity(name = "equipo")
public class Equipment {

    @Id
    private String id;
    @Column(name = "tarjeta_madre")
    private String motherBoard;
    @Column(name = "capacidad_ram")
    private String ramCapacity;
    @Column(name = "almacenamiento")
    private String storage;
    @Column(name = "procesador")
    private String processor;
    @Column(name = "sistema_operativo")
    private String operatingSystem;
    @Column(name = "estado")
    private String status;
    @Column(name = "cliente_id")
    private String customerId;

}
