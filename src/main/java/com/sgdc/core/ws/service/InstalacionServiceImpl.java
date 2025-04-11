package com.sgdc.core.ws.service;

import com.sgdc.core.ws.exception.ResourceAlreadyExistsException;
import com.sgdc.core.ws.exception.ResourceNotFoundException;
import com.sgdc.core.ws.model.Instalacion;
import com.sgdc.core.ws.repository.InstalacionRepository;
import jakarta.persistence.criteria.Expression;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstalacionServiceImpl implements InstalacionService {

    private final InstalacionRepository repository;

    public InstalacionServiceImpl(InstalacionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Instalacion> findAll() {
        return repository.findAll();
    }

    @Override
    public Instalacion findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("La instalación no existe"));
    }

    @Override
    public List<Instalacion> search(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return repository.findAll();
        }
        String pattern = "%" + keyword.toLowerCase() + "%";

        Specification<Instalacion> spec = (root, query, cb) -> {
            // Para atributos de tipo String se hace directamente.
            Expression<String> nombreExpr = cb.lower(root.get("nombre"));
            //Expression<String> descripcionExpr = cb.lower(root.get("descripcion"));
            // Para campos tipo TEXT se deberá usar la siguiente expresión, la expresión de arriba no es funcional.
            Expression<String> descripcionExpr = cb.function("concat", String.class, root.get("descripcion"), cb.literal(""));
            Expression<String> estadoExpr = cb.lower(root.get("estado"));

            return cb.or(
                    cb.like(nombreExpr, pattern),
                    cb.like(descripcionExpr, pattern),
                    cb.like(estadoExpr, pattern)
            );
        };

        return repository.findAll(spec);
    }

    @Override
    public Instalacion save(Instalacion instalacion) {
        Optional<Instalacion> existingInstalacion = repository.findByNombre(instalacion.getNombre());
        if (existingInstalacion.isPresent()) {
            throw new ResourceAlreadyExistsException("La instalación ya existe");
        }
        return repository.save(instalacion);
    }

    @Override
    public Instalacion update(Integer id, Instalacion instalacion) {
        Optional<Instalacion> existingInstalacion = repository.findById(id);
        if (existingInstalacion.isPresent()) {
            Instalacion updatedInstalacion = existingInstalacion.get();
            updatedInstalacion.setNombre(instalacion.getNombre());
            updatedInstalacion.setDescripcion(instalacion.getDescripcion());
            updatedInstalacion.setEstado(instalacion.getEstado());
            return repository.save(updatedInstalacion);
        } else {
            throw new ResourceNotFoundException("La instalación no existe");
        }
    }

    @Override
    public void delete(Integer id) {
        Optional<Instalacion> existingInstalacion = repository.findById(id);
        if (existingInstalacion.isPresent()) {
            repository.delete(existingInstalacion.get());
        } else {
            throw new ResourceNotFoundException("La instalación no existe");
        }
    }
}
