package com.sgdc.core.ws.util;

import java.util.Arrays;

public class EnumMapperUtils {
    /**
     * Convierte una etiqueta (`label`) a un valor Enum, usando el metodo getLabel().
     *
     * @param enumClass Clase del Enum
     * @param label     Etiqueta proporcionada (por ejemplo, "Masculino")
     * @param <T>       Tipo del Enum
     * @return Enum correspondiente
     * @throws IllegalArgumentException si no se encuentra una coincidencia
     */
    public static <T extends Enum<T> & LabeledEnum> T fromLabel(Class<T> enumClass, String label) {
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(e -> e.getLabel().equalsIgnoreCase(label))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "No se encontró valor para etiqueta: " + label + " en enum " + enumClass.getSimpleName()));
    }

    public interface LabeledEnum {
        String getLabel();
    }
}
