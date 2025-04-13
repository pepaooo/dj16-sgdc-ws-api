package com.sgdc.core.ws.controller;

import com.sgdc.core.ws.model.Instalacion;
import com.sgdc.core.ws.service.InstalacionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/instalaciones")
public class InstalacionController {

    private final InstalacionService instalacionService;

    public InstalacionController(InstalacionService instalacionService) {
        this.instalacionService = instalacionService;
    }

    @GetMapping
    public ResponseEntity<List<Instalacion>> findAll(@RequestParam(value = "q", required = false) String keyword, Model model) {
        List<Instalacion> instalaciones = instalacionService.search(keyword);
        return ResponseEntity.ok(instalaciones);
    }

    @GetMapping("{id}")
    public ResponseEntity<Instalacion> getInstalacion(@PathVariable Integer id) {
        Instalacion instalacion = instalacionService.findById(id);
        return ResponseEntity.ok(instalacion);
    }

    @PostMapping
    public ResponseEntity<Instalacion> guardarInstalacion(@Valid @RequestBody Instalacion instalacion) {
        Instalacion instalacionCreada = instalacionService.save(instalacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(instalacionCreada);
    }

    @PutMapping("{id}")
    public ResponseEntity<Instalacion> changeInstalacion(@PathVariable Integer id, @Valid @RequestBody Instalacion instalacion) {
        Instalacion updatedInstalacion = instalacionService.update(id, instalacion);
        return ResponseEntity.ok(updatedInstalacion);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteInstalacion(@PathVariable Integer id) {
        instalacionService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
