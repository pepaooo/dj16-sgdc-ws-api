package com.sgdc.core.ws.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;


@Entity
@Table(name = "instalacion")
@NamedQueries({
        @NamedQuery(
                name = "Instalacion.findByNombreExacto",
                query = "SELECT i FROM Instalacion i WHERE i.nombre = :nombre"
        ),
        @NamedQuery(
                name = "Instalacion.findByNombreYEstado",
                query = "SELECT i FROM Instalacion i WHERE i.estado = :estado AND i.nombre = :nombre"
        )
})
@Data
public class Instalacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_instalacion", nullable = false)
    private Integer id;

    @NotBlank(message = "El nombre de la instalación no puede estar vacío")
    @Size(max = 100, message = "El nombre de la instalación no puede tener más de 100 caracteres")
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Lob
    @NotBlank(message = "La descripción de la instalación no puede estar vacía")
    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @ColumnDefault("'Disponible'")
    @Column(name = "estado", nullable = false, length = 20)
    //@Enumerated(EnumType.STRING)
    private String estado;
    //private EstadoInstalacion estado;

}