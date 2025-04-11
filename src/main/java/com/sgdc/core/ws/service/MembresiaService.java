package com.sgdc.core.ws.service;


import com.sgdc.core.ws.model.Membresia;

import java.util.List;
import java.util.Optional;

public interface MembresiaService {

    List<Membresia> findAll();

    Membresia findById(Integer id);

    List<Membresia> search(String keyword);

    Membresia save(Membresia membresia);

    Optional<Membresia> activateMembresia(Integer id);

    Optional<Membresia> deactivateMembresia(Integer id);

    Membresia update(Integer id, Membresia membresia);

    void delete(Integer id);

}
