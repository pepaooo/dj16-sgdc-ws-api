package com.sgdc.core.ws.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class MiembroDTO {
    private Integer id;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String direccion;
    private String telefono;
    private String correoElectronico;
    private LocalDate fechaNacimiento;
    private String genero; // Aquí va el label ("Masculino", "Femenino", "Otro")
    private LocalDateTime fechaInscripcion;
    private Integer idMembresia;
    private String nombreMembresia; // opcional si deseas mostrarlo también
}
