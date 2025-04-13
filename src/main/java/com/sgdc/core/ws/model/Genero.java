package com.sgdc.core.ws.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Genero {
    M("Masculino"),
    F("Femenino"),
    O("Otro");

    private final String label;

    Genero(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }
}