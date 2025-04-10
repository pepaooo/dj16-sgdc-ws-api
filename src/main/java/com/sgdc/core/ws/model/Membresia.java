package com.sgdc.core.ws.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Table(name = "membresia")
@NamedQueries({
        @NamedQuery(
                name = "TipoMembresia.findByNombreExacto",
                query = "SELECT t FROM Membresia t WHERE t.nombre = :nombre"
        ),
        @NamedQuery(
                name = "TipoMembresia.findByTarifaMenor",
                query = "SELECT t FROM Membresia t WHERE t.tarifa < :tarifa"
        )
})
@Data
public class Membresia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_membresia", nullable = false)
    private Integer id;

    @NotBlank(message = "El nombre de la membresía no puede estar vacío")
    @Size(max = 50, message = "El nombre de la membresía no puede tener más de 50 caracteres")
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Min(value = 0, message = "La tarifa no puede ser negativa")
    @Column(name = "tarifa", nullable = false, precision = 10, scale = 2)
    private BigDecimal tarifa;

    @Min(value = 0, message = "La duración en días no puede ser negativa")
    @Column(name = "duracion_dias", nullable = false)
    private Integer duracionDias;

    @Column(name = "estatus", nullable = false)
    private String estatus;

    @NotBlank(message = "La descripción de la membresía no puede estar vacía")
    @Size(max = 255, message = "La descripción de la membresía no puede tener más de 255 caracteres")
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

}