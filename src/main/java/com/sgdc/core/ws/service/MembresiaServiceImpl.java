package com.sgdc.core.ws.service;

import com.sgdc.core.ws.exception.ResourceAlreadyExistsException;
import com.sgdc.core.ws.exception.ResourceNotFoundException;
import com.sgdc.core.ws.model.Instalacion;
import com.sgdc.core.ws.model.Membresia;
import com.sgdc.core.ws.repository.MembresiaRepository;
import jakarta.persistence.criteria.Expression;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembresiaServiceImpl implements MembresiaService {

    private final MembresiaRepository repository;

    public MembresiaServiceImpl(MembresiaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Membresia> findAll() {
        return repository.findAll();
    }

    @Override
    public Membresia findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("La membresía no existe"));
    }

    @Override
    public List<Membresia> search(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return repository.findAll();
        }
        String pattern = "%" + keyword.toLowerCase() + "%";

        Specification<Membresia> spec = (root, query, cb) -> {
            // Para atributos de tipo String se hace directamente.
            Expression<String> nombreExpr = cb.lower(root.get("nombre"));
            Expression<String> estatusExpr = cb.lower(root.get("estatus"));
            // Para atributos numéricos, se puede usar una función para convertir a cadena, si lo soporta el dialecto
            Expression<String> tarifaExpr = cb.function("str", String.class, root.get("tarifa"));
            Expression<String> duracionExpr = cb.function("str", String.class, root.get("duracionDias"));

            return cb.or(
                    cb.like(nombreExpr, pattern),
                    cb.like(estatusExpr, pattern),
                    cb.like(cb.lower(tarifaExpr), pattern),
                    cb.like(cb.lower(duracionExpr), pattern)
            );
        };

        return repository.findAll(spec);
    }

    @Override
    public Membresia save(Membresia membresia) {
        Optional<Membresia> existingMembresia = repository.findByNombre(membresia.getNombre());
        if (existingMembresia.isPresent()) {
            throw new ResourceAlreadyExistsException("La membresía ya existe");
        }
        return repository.save(membresia);
    }

    @Override
    public Optional<Membresia> activateMembresia(Integer id) {
        Optional<Membresia> membresia = repository.findById(id);
        if (membresia.isPresent()) {
            Membresia m = membresia.get();
            m.setEstatus("Activo");
            return Optional.of(repository.save(m));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Membresia> deactivateMembresia(Integer id) {
        Optional<Membresia> membresia = repository.findById(id);
        if (membresia.isPresent()) {
            Membresia m = membresia.get();
            m.setEstatus("Inactivo");
            return Optional.of(repository.save(m));
        }
        return Optional.empty();
    }

    @Override
    public Membresia update(Integer id, Membresia membresia) {
        Optional<Membresia> existingMembresia = repository.findById(id);
        if (existingMembresia.isPresent()) {
            Membresia updatedMembresia = existingMembresia.get();
            updatedMembresia.setNombre(membresia.getNombre());
            updatedMembresia.setDescripcion(membresia.getDescripcion());
            updatedMembresia.setEstatus(membresia.getEstatus());
            updatedMembresia.setTarifa(membresia.getTarifa());
            updatedMembresia.setDuracionDias(membresia.getDuracionDias());
            return repository.save(updatedMembresia);
        } else {
            throw new ResourceNotFoundException("La membresía no existe");
        }
    }

    @Override
    public void delete(Integer id) {
        Optional<Membresia> existingMembresia = repository.findById(id);
        if (existingMembresia.isPresent()) {
            repository.delete(existingMembresia.get());
        } else {
            throw new ResourceNotFoundException("La membresía no existe");
        }
    }
}
