package com.sgdc.core.ws.config;

import com.sgdc.core.ws.dto.MiembroDTO;
import com.sgdc.core.ws.model.Miembro;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        // Converter de Miembro → MiembroDTO
        Converter<Miembro, MiembroDTO> miembroToDtoConverter = ctx -> {
            Miembro source = ctx.getSource();
            MiembroDTO dto = new MiembroDTO();
            dto.setId(source.getId());
            dto.setNombre(source.getNombre());
            dto.setApellidoPaterno(source.getApellidoPaterno());
            dto.setApellidoMaterno(source.getApellidoMaterno());
            dto.setDireccion(source.getDireccion());
            dto.setTelefono(source.getTelefono());
            dto.setCorreoElectronico(source.getCorreoElectronico());
            dto.setFechaNacimiento(source.getFechaNacimiento());
            dto.setFechaInscripcion(source.getFechaInscripcion());

            if (source.getGenero() != null) {
                dto.setGenero(source.getGenero().getLabel());
            }

            if (source.getMembresia() != null) {
                dto.setIdMembresia(source.getMembresia().getId());
                dto.setNombreMembresia(source.getMembresia().getNombre());
            }

            return dto;
        };

        mapper.createTypeMap(Miembro.class, MiembroDTO.class)
                .setConverter(miembroToDtoConverter);

        // DTO → ENTIDAD: se deja en blanco, se mapea manualmente en el servicio
        mapper.addMappings(new PropertyMap<MiembroDTO, Miembro>() {
            @Override
            protected void configure() {
                skip(destination.getGenero());
                skip(destination.getMembresia());
            }
        });

        return mapper;
    }

}
