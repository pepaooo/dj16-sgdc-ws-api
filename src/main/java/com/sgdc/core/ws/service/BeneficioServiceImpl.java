package com.sgdc.core.ws.service;

import com.sgdc.core.ws.exception.BeneficioAlreadyExistsException;
import com.sgdc.core.ws.exception.BeneficioNotFoundException;
import com.sgdc.core.ws.model.Beneficio;
import com.sgdc.core.ws.repository.BeneficioRepository;
import jakarta.persistence.criteria.Expression;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BeneficioServiceImpl implements BeneficioService {

    private final BeneficioRepository repository;

    public BeneficioServiceImpl(BeneficioRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Beneficio> findAll() {
        return repository.findAll();
    }

    @Override
    public Beneficio findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new BeneficioNotFoundException("El beneficio no existe"));
    }

    @Override
    public List<Beneficio> search(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return repository.findAll();
        }
        String pattern = "%" + keyword.toLowerCase() + "%";

        Specification<Beneficio> spec = (root, query, cb) -> {
            // Para atributos de tipo String se hace directamente.
            Expression<String> nombreExpr = cb.lower(root.get("nombre"));
            Expression<String> estatusExpr = cb.lower(root.get("descripcion"));

            return cb.or(
                    cb.like(nombreExpr, pattern),
                    cb.like(estatusExpr, pattern)
            );
        };

        return repository.findAll(spec);
    }

    @Override
    public Beneficio save(Beneficio beneficio) {
        Optional<Beneficio> existingBeneficio = repository.findByNombre(beneficio.getNombre());
        if (existingBeneficio.isPresent()) {
            throw new BeneficioAlreadyExistsException("El beneficio ya existe");
        }
        return repository.save(beneficio);
    }

   @Override
    public Beneficio update(Integer id, Beneficio beneficio) {
       Optional<Beneficio> existingBeneficio = repository.findById(id);
         if (existingBeneficio.isPresent()) {
              Beneficio updatedBeneficio = existingBeneficio.get();
              updatedBeneficio.setNombre(beneficio.getNombre());
              updatedBeneficio.setDescripcion(beneficio.getDescripcion());
              return repository.save(updatedBeneficio);
         } else {
              throw new BeneficioNotFoundException("El beneficio no existe");
         }
   }

    @Override
    public void delete(Integer id) {
        Optional<Beneficio> existingBeneficio = repository.findById(id);
        if (existingBeneficio.isPresent()) {
            repository.delete(existingBeneficio.get());
        } else {
            throw new BeneficioNotFoundException("El beneficio no existe");
        }
    }
}
