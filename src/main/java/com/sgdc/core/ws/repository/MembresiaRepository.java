package com.sgdc.core.ws.repository;

import com.sgdc.core.ws.model.Membresia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface MembresiaRepository extends JpaRepository<Membresia, Integer>, JpaSpecificationExecutor<Membresia> {
    Optional<Membresia> findByNombre(String nombre);
}
