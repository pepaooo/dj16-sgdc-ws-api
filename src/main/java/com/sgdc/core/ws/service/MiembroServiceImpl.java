package com.sgdc.core.ws.service;

import com.sgdc.core.ws.dto.MiembroDTO;
import com.sgdc.core.ws.mapper.MiembroMapper;
import com.sgdc.core.ws.exception.ResourceAlreadyExistsException;
import com.sgdc.core.ws.exception.ResourceNotFoundException;
import com.sgdc.core.ws.model.Genero;
import com.sgdc.core.ws.model.Membresia;
import com.sgdc.core.ws.model.Miembro;
import com.sgdc.core.ws.repository.MembresiaRepository;
import com.sgdc.core.ws.repository.MiembroRepository;
import jakarta.persistence.criteria.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.sgdc.core.ws.util.EnumMapperUtils.fromLabel;

@Service
public class MiembroServiceImpl implements MiembroService {

    private static final Logger log = LoggerFactory.getLogger(MiembroServiceImpl.class);

    private final MiembroRepository repository;

    private final MembresiaRepository membresiaRepository;

    private final MiembroMapper mapper;

    public MiembroServiceImpl(MiembroRepository repository, MembresiaRepository membresiaRepository, MiembroMapper mapper) {
        this.repository = repository;
        this.membresiaRepository = membresiaRepository;
        this.mapper = mapper;
    }

    @Override
    public List<MiembroDTO> findAll() {
        return List.of();
    }

    @Override
    public MiembroDTO findById(Integer id) {
        Miembro miembro = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("El miembro no existe"));
        log.info("Miembro encontrado: {}", miembro);
        MiembroDTO miembroDTO = mapper.toDto(miembro);
        log.info("MiembroDTO encontrado: {}", miembroDTO);
        return miembroDTO;
    }

    @Override
    public List<MiembroDTO> search(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
        }
        String pattern = "%" + keyword.toLowerCase() + "%";

        Specification<Miembro> spec = (root, query, cb) -> {
            // Para atributos de tipo String se hace directamente.
            Expression<String> nombreExpr = cb.lower(root.get("nombre"));
            Expression<String> apellidoPaternoExpr = cb.lower(root.get("apellidoPaterno"));
            Expression<String> apellidoMaternoExpr = cb.lower(root.get("apellidoMaterno"));
            Expression<String> correoElectronicoExpr = cb.lower(root.get("correoElectronico"));

            return cb.or(
                    cb.like(nombreExpr, pattern),
                    cb.like(apellidoPaternoExpr, pattern),
                    cb.like(apellidoMaternoExpr, pattern),
                    cb.like(cb.lower(correoElectronicoExpr), pattern)
            );
        };

        return repository.findAll(spec, Sort.by(Sort.Direction.DESC, "id"))
                .stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public MiembroDTO save(MiembroDTO miembroDTO) {
        // Mapeamos el DTO a la entidad
        Miembro miembro = mapper.toEntity(miembroDTO);
        miembro.setGenero(fromLabel(Genero.class, miembroDTO.getGenero()));

        Optional<Miembro> optional = repository.findByCorreoElectronico(miembro.getCorreoElectronico());
        if (optional.isPresent()) {
            throw new ResourceAlreadyExistsException("El miembro ya existe registrado con ese correo electrónico");
        }

        Optional<Membresia> membresiaOptional = membresiaRepository.findById(miembroDTO.getIdMembresia());
        if (membresiaOptional.isEmpty()) {
            throw new ResourceNotFoundException("La membresía no existe");
        }
        miembro.setMembresia(membresiaOptional.get());

        // Guardamos el miembro
        Miembro savedMiembro = repository.save(miembro);
        return mapper.toDto(savedMiembro);
    }

    @Override
    public MiembroDTO update(Integer id, MiembroDTO miembroDTO) {
        // Mapeamos el DTO a la entidad
        Miembro miembro = mapper.toEntity(miembroDTO);
        miembro.setId(id);
        miembro.setGenero(fromLabel(Genero.class, miembroDTO.getGenero()));

        Optional<Miembro> optional = repository.findByCorreoElectronico(miembro.getCorreoElectronico());
        if (optional.isPresent() && !optional.get().getId().equals(id)) {
            throw new ResourceAlreadyExistsException("El miembro ya existe registrado con ese correo electrónico");
        }

        Optional<Membresia> membresiaOptional = membresiaRepository.findById(miembroDTO.getIdMembresia());
        if (membresiaOptional.isEmpty()) {
            throw new ResourceNotFoundException("La membresía no existe");
        }
        miembro.setMembresia(membresiaOptional.get());

        // Guardamos el miembro
        Miembro updatedMiembro = repository.save(miembro);
        return mapper.toDto(updatedMiembro);
    }

    @Override
    public void delete(Integer id) {
        Optional<Miembro> optional = repository.findById(id);
        if (optional.isPresent()) {
            repository.delete(optional.get());
        } else {
            throw new ResourceNotFoundException("El miembro no existe");
        }
    }

}
