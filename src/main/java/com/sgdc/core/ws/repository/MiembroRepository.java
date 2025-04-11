package com.sgdc.core.ws.repository;

import com.sgdc.core.ws.model.Membresia;
import com.sgdc.core.ws.model.Miembro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface MiembroRepository extends JpaRepository<Miembro, Integer>, JpaSpecificationExecutor<Miembro> {
    Optional<Miembro> findByNombreAndApellidoPaternoAndApellidoMaternoAndCorreoElectronico(
            String nombre, String apellidoPaterno, String apellidoMaterno, String correoElectronico);

    Optional<Miembro> findByNombreAndApellidoPaternoAndApellidoMaternoAndCorreoElectronicoAndMembresia(
            String nombre, String apellidoPaterno, String apellidoMaterno, String correoElectronico, Membresia membresia);

    Optional<Miembro> findByNombreAndApellidoPaternoAndApellidoMaterno(String nombre, String apellidoPaterno, String apellidoMaterno);

    Optional<Miembro> findByCorreoElectronico(String correoElectronico);
}
