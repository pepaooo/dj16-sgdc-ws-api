package com.sgdc.core.ws.repository;

import com.sgdc.core.ws.model.Instalacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface InstalacionRepository extends JpaRepository<Instalacion, Integer>, JpaSpecificationExecutor<Instalacion> {
    Optional<Instalacion> findByNombre(String nombre);
}
