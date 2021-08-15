package com.poli.servicedesk.enums;

import java.util.Arrays;

public enum RolEnum {

    REDES("afaade8d-63e6-4c2b-b39c-bbdbce8eafaf"),
    MANTENIMIENTO ("69eb52e1-dc3b-4524-a473-587c87af85e1"),
    OFIMATICA ("2f31778b-4cb0-4c9d-b6c7-e5eed99fd1f5");

    private RolEnum(String id) {
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
                .map(RolEnum::getId).findFirst()
                .orElseThrow(() -> new RuntimeException("OBJECT_NAME_NOT_VALID"));
    }

    public String getId() {
        return this.id;
    }

}
