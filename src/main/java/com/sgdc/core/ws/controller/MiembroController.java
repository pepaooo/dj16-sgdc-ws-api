package com.sgdc.core.ws.controller;

import com.sgdc.core.ws.model.Miembro;
import com.sgdc.core.ws.service.MiembroService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/miembros")
public class MiembroController {

    private static final Logger logger = LoggerFactory.getLogger(MiembroController.class);
    
    private final MiembroService miembroService;

    public MiembroController(MiembroService miembroService) {
        this.miembroService = miembroService;
    }

    @GetMapping
    public ResponseEntity<List<Miembro>> findAll(@RequestParam(value = "q", required = false) String keyword, Model model) {
        List<Miembro> miembros = miembroService.search(keyword);
        return ResponseEntity.ok(miembros);
    }

    @GetMapping("{id}")
    public ResponseEntity<Miembro> getMiembro(@PathVariable Integer id) {
        Miembro miembro = miembroService.findById(id);
        return ResponseEntity.ok(miembro);
    }

    @PostMapping
    public ResponseEntity<Miembro> guardarMiembro(@Valid @RequestBody Miembro miembro) {
        Miembro miembroCreada = miembroService.save(miembro);
        Miembro newMiembro = miembroService.findById(miembroCreada.getId());
        logger.info("Miembro created: {}", newMiembro);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMiembro);
    }

    @PutMapping("{id}")
    public ResponseEntity<Miembro> changeMiembro(@PathVariable Integer id, @Valid @RequestBody Miembro miembro) {
        Miembro updatedMiembro = miembroService.update(id, miembro);
        return ResponseEntity.ok(updatedMiembro);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMiembro(@PathVariable Integer id) {
        miembroService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
