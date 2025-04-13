package com.sgdc.core.ws.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = EnumValueValidator.class)
@Target({ FIELD, METHOD, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface EnumValue {
    String message() default "El valor no es válido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    // Clase del enum contra el que se validará el valor
    Class<? extends Enum<?>> enumClass();

    // Nombre del método en el enum que se usará para obtener el valor (por ejemplo, getLabel)
    String enumMethod() default "name";
}

