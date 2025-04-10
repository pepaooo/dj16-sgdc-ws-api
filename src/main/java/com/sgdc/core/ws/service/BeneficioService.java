package com.sgdc.core.ws.service;


import com.sgdc.core.ws.model.Beneficio;

import java.util.List;
import java.util.Optional;

public interface BeneficioService {
    List<Beneficio> findAll();

    Optional<Beneficio> findById(Integer id);

    List<Beneficio> search(String keyword);

    void save(Beneficio beneficio);

    void delete(Integer id);
}
