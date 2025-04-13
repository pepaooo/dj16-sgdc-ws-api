package com.sgdc.core.ws.dto;

import com.sgdc.core.ws.model.Genero;
import com.sgdc.core.ws.validation.EnumValue;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class MiembroDTO {

    private Integer id;

    @NotBlank(message = "El nombre del miembro no puede estar vacío")
    private String nombre;

    @NotBlank(message = "El apellido paterno del miembro no puede estar vacío")
    @Size(max = 50, message = "El apellido paterno del miembro no pueden tener más de 50 caracteres")
    private String apellidoPaterno;

    @NotBlank(message = "El apellido materno del miembro no puede estar vacío")
    @Size(max = 50, message = "El apellido materno del miembro no pueden tener más de 50 caracteres")
    private String apellidoMaterno;

    @NotBlank(message = "La dirección del miembro no puede estar vacía")
    @Size(max = 255, message = "La dirección del miembro no puede tener más de 255 caracteres")
    private String direccion;

    @NotBlank(message = "El teléfono del miembro no puede estar vacío")
    @Size(max = 20, message = "El teléfono del miembro no puede tener más de 20 caracteres")
    private String telefono;

    @NotBlank(message = "El correo electrónico del miembro no puede estar vacío")
    @Email(message = "El correo electrónico del miembro no es válido")
    private String correoElectronico;

    @NotNull(message = "La fecha de nacimiento del miembro no puede estar vacía")
    @Past(message = "La fecha de nacimiento del miembro no es válida")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;

    @NotNull(message = "El género del miembro no puede estar vacío")
//    @Pattern(regexp = "Masculino|Femenino|Otro", message = "El género del miembro no es válido")
    @EnumValue(enumClass = Genero.class, enumMethod = "getLabel", message = "El género del miembro no es válido")
    private String genero; // Aquí va el label ("Masculino", "Femenino", "Otro")

    private LocalDateTime fechaInscripcion;

    @NotNull(message = "El identificador de la membresía del miembro no puede estar vacía")
    private Integer idMembresia;

    private String nombreMembresia; // opcional si deseas mostrarlo también
}
