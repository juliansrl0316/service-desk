package com.poli.servicedesk.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EquipmentDTO {

    private String id;
    private String motherBoard;
    private String ramCapacity;
    private String storage;
    private String processor;
    private String operatingSystem;
    private String status;
    private String customerId;

}
