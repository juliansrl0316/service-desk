package com.poli.servicedesk.enums;

import java.util.Arrays;

public enum IncidentTypeEnum {

    REDES("ab171219-fb9a-422a-badd-9ea565dcc861"),
    MANTENIMIENTO ("76de7780-c0a3-423a-8b73-0ada464c395f"),
    OFIMATICA ("863e7376-f09e-407a-84b6-e132f8ce1df7");

    private IncidentTypeEnum(String id) {
        this.id = id;
    }

    private final String id;

    public static String nameFromId(String id) {
        return Arrays.stream(values())
                .filter(dse -> dse.getId().contentEquals(id))
                .map(Enum::name).findFirst()
                .orElseThrow(() -> new RuntimeException("OBJECT_STATUS_NOT_VALID"));
    }

    public static String idFromName(String name) {
        return Arrays.stream(values())
                .filter(dse -> dse.name().contentEquals(name))
                .map(IncidentTypeEnum::getId).findFirst()
                .orElseThrow(() -> new RuntimeException("OBJECT_NAME_NOT_VALID"));
    }

    public String getId() {
        return this.id;
    }

}
