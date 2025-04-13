package com.sgdc.core.ws.service;

import com.sgdc.core.ws.dto.MiembroDTO;
import com.sgdc.core.ws.exception.ResourceAlreadyExistsException;
import com.sgdc.core.ws.exception.ResourceNotFoundException;
import com.sgdc.core.ws.model.Miembro;
import com.sgdc.core.ws.repository.MiembroRepository;
import jakarta.persistence.criteria.Expression;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MiembroServiceImpl implements MiembroService {

    private static final Logger log = LoggerFactory.getLogger(MiembroServiceImpl.class);

    private final MiembroRepository repository;

    private final ModelMapper modelMapper;

    public MiembroServiceImpl(MiembroRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<MiembroDTO> findAll() {
        return List.of();
    }

    @Override
    public MiembroDTO findById(Integer id) {
        Miembro miembro = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("El miembro no existe"));
        log.info("Miembro encontrado: {}", miembro);
        return modelMapper.map(miembro, MiembroDTO.class);
    }

    @Override
    public List<Miembro> search(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return repository.findAll();
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

        return repository.findAll(spec, Sort.by(Sort.Direction.DESC, "id"));
    }

    @Override
    public Miembro save(Miembro miembro) {
        Optional<Miembro> optional = repository.findByCorreoElectronico(miembro.getCorreoElectronico());
        if (optional.isPresent()) {
            throw new ResourceAlreadyExistsException("El miembro ya existe");
        }
        return repository.save(miembro);
    }

    @Override
    public Miembro update(Integer id, Miembro miembro) {
        Optional<Miembro> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new ResourceNotFoundException("El miembro no existe");
        }
        Miembro existingMiembro = optional.get();
        existingMiembro.setNombre(miembro.getNombre());
        existingMiembro.setApellidoPaterno(miembro.getApellidoPaterno());
        existingMiembro.setApellidoMaterno(miembro.getApellidoMaterno());
        existingMiembro.setDireccion(miembro.getDireccion());
        existingMiembro.setTelefono(miembro.getTelefono());
        existingMiembro.setCorreoElectronico(miembro.getCorreoElectronico());
        existingMiembro.setFechaNacimiento(miembro.getFechaNacimiento());
        return repository.save(existingMiembro);
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
