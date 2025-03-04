package com.skoy.bootcamp_microservices.enums;

public enum StatusEnum {
    DELETED("Eliminado"),
    ENABLED("Habilitado"),
    DISABLED("Deshabilitado");

    private final String name;

    StatusEnum(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
