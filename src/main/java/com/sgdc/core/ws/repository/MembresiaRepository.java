package com.sgdc.core.ws.repository;

import com.sgdc.core.ws.model.Membresia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MembresiaRepository extends JpaRepository<Membresia, Integer>, JpaSpecificationExecutor<Membresia> {
}
