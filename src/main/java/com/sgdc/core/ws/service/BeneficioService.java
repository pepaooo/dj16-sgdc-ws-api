package com.sgdc.core.ws.service;


import com.sgdc.core.ws.model.Beneficio;

import java.util.List;
import java.util.Optional;

public interface BeneficioService {
    List<Beneficio> findAll();

    Beneficio findById(Integer id);

    List<Beneficio> search(String keyword);

    Beneficio save(Beneficio beneficio);

    Beneficio update(Integer id, Beneficio beneficio);

    void delete(Integer id);
}
