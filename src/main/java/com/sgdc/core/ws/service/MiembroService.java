package com.sgdc.core.ws.service;


import com.sgdc.core.ws.dto.MiembroDTO;
import com.sgdc.core.ws.model.Miembro;

import java.util.List;

public interface MiembroService {

    List<MiembroDTO> findAll();

    MiembroDTO findById(Integer id);

    List<MiembroDTO> search(String keyword);

    MiembroDTO save(MiembroDTO miembroDTO);

    MiembroDTO update(Integer id, MiembroDTO miembroDTO);

    void delete(Integer id);

}
