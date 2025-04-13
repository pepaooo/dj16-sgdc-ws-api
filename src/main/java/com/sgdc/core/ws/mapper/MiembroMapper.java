package com.sgdc.core.ws.mapper;

import com.sgdc.core.ws.dto.MiembroDTO;
import com.sgdc.core.ws.model.Miembro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MiembroMapper {
    @Mapping(source = "membresia.id", target = "idMembresia")
    @Mapping(source = "membresia.nombre", target = "nombreMembresia")
    @Mapping(source = "genero.label", target = "genero")
    MiembroDTO toDto(Miembro miembro);

    @Mapping(target = "genero", ignore = true) // lo seteas manual
    @Mapping(target = "membresia", ignore = true) // también
    Miembro toEntity(MiembroDTO dto);
}

