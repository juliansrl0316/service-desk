package com.poli.servicedesk.enums;

import java.util.Arrays;

public enum StatusEnum {

    ACTIVE("ed6256dc-c24e-451e-a0ec-f6ba90917eab"),
    DISABLED ("f9e2ee38-cb29-4a01-9a8c-f160d2ac0960");

    private final String id;

    private StatusEnum(String id) {
        this.id = id;
    }

    public static String nameFromId(String id) {
        return Arrays.stream(values())
                .filter(dse -> dse.getId().contentEquals(id))
                .map(Enum::name).findFirst()
                .orElseThrow(() -> new RuntimeException("OBJECT_STATUS_NOT_VALID"));
    }

    public static String idFromName(String name) {
        return Arrays.stream(values())
                .filter(dse -> dse.name().contentEquals(name))
                .map(StatusEnum::getId).findFirst()
                .orElseThrow(() -> new RuntimeException("OBJECT_NAME_NOT_VALID"));
    }

    public String getId() {
        return this.id;
    }
}
