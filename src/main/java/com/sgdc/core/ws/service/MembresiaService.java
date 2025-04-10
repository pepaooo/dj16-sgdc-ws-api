package com.sgdc.core.ws.service;


import com.sgdc.core.ws.model.Membresia;

import java.util.List;
import java.util.Optional;

public interface MembresiaService {

    List<Membresia> findAll();

    Optional<Membresia> findById(Integer id);

    List<Membresia> search(String keyword);

    void save(Membresia membresia);

    Optional<Membresia> activateMembresia(Integer id);

    Optional<Membresia> deactivateMembresia(Integer id);

}
