package com.sgdc.core.ws.service;


import com.sgdc.core.ws.dto.MiembroDTO;
import com.sgdc.core.ws.model.Miembro;

import java.util.List;

public interface MiembroService {

    List<MiembroDTO> findAll();

    MiembroDTO findById(Integer id);

    List<Miembro> search(String keyword);

    Miembro save(Miembro Miembro);

    Miembro update(Integer id, Miembro Miembro);

    void delete(Integer id);

}
