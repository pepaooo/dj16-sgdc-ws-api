package com.sgdc.core.ws.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class EnumValueValidator implements ConstraintValidator<EnumValue, String> {

    private Set<String> acceptedValues;

    private String enumMethod;

    @Override
    public void initialize(EnumValue constraintAnnotation) {
        Class<? extends Enum<?>> enumClass = constraintAnnotation.enumClass();
        this.enumMethod = constraintAnnotation.enumMethod();
        acceptedValues = Arrays.stream(enumClass.getEnumConstants())
                .map(e -> {
                    try {
                        Method method = enumClass.getMethod(enumMethod);
                        return (String) method.invoke(e);
                    } catch (Exception ex) {
                        throw new RuntimeException("Error al invocar el método " + enumMethod + " en el enum " + enumClass.getSimpleName(), ex);
                    }
                })
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Permitir null o vacío si se valida con @NotNull en otro lugar
        return value == null || acceptedValues.contains(value);
    }
}

