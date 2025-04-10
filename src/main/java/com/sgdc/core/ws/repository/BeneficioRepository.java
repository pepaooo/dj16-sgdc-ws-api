package com.sgdc.core.ws.repository;

import com.sgdc.core.ws.model.Beneficio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface BeneficioRepository extends JpaRepository<Beneficio, Integer>, JpaSpecificationExecutor<Beneficio> {
    Optional<Beneficio> findByNombre(String nombre);
}
