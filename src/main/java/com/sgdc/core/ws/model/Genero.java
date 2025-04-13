package com.sgdc.core.ws.model;

import com.fasterxml.jackson.annotation.JsonValue;
import com.sgdc.core.ws.util.EnumMapperUtils;

public enum Genero implements EnumMapperUtils.LabeledEnum {
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