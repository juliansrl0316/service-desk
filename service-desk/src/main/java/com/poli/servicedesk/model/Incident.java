package com.poli.servicedesk.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity(name = "incidente")
public class Incident {

    @Id
    private String id;
    @Column(name = "fecha_registro")
    private Date registerDate;
    @Column(name = "estado")
    private String status;
    @Column(name = "tipo_incidente_id")
    private String incidentTypeId;
    @Column(name = "empleado_id")
    private String employeeId;
    @Column(name = "cliente_id")
    private String customerId;

}
