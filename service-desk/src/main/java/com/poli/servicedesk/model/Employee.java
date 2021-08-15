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
@Entity(name = "empleado")
public class Employee {

    @Id
    private String id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "primer_apellido")
    private String firstName;
    @Column(name = "segundo_apellido")
    private String secondName;
    @Column(name = "n_documento")
    private String documentNumber;
    @Column(name = "estado")
    private String status;
    @Column(name = "rol_id")
    private String rolId;

}
