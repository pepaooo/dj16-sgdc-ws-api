package com.sgdc.core.ws.mapper;

import com.sgdc.core.ws.dto.MiembroDTO;
import com.sgdc.core.ws.model.Miembro;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface MiembroMapper {
    @Mapping(source = "apellidoPaterno", target = "paterno")
    @Mapping(source = "apellidoMaterno", target = "materno")
    @Mapping(source = "correoElectronico", target = "correo")
    @Mapping(source = "membresia.id", target = "idMembresia")
    @Mapping(source = "membresia.nombre", target = "nombreMembresia")
    @Mapping(source = "genero.label", target = "genero")
    MiembroDTO toDto(Miembro miembro);

    @InheritInverseConfiguration
    @Mapping(target = "genero", ignore = true) // se realizará de manera manual
    @Mapping(target = "membresia", ignore = true) // también
    @Mapping(target = "fechaInscripcion", ignore = true) // se asigna automáticamente
    Miembro toEntity(MiembroDTO dto);

    @InheritConfiguration(name = "toEntity")
    @Mapping(target = "id", ignore = true)
    void updateFromDto(MiembroDTO dto, @MappingTarget Miembro miembro);
}

