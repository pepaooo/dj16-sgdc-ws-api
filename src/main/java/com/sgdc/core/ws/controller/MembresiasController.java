package com.sgdc.core.ws.controller;

import com.sgdc.core.ws.model.Membresia;
import com.sgdc.core.ws.service.MembresiaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/membresias")
public class MembresiasController {

    private final MembresiaService membresiaService;

    public MembresiasController(MembresiaService membresiaService) {
        this.membresiaService = membresiaService;
    }

    @GetMapping
    public ResponseEntity<List<Membresia>> findAll(@RequestParam(value = "q", required = false) String keyword, Model model) {
        List<Membresia> membresias = membresiaService.search(keyword);
        return ResponseEntity.ok(membresias);
    }

    @GetMapping("{id}")
    public ResponseEntity<Membresia> getMembresia(@PathVariable Integer id) {
        Membresia membresia = membresiaService.findById(id);
        return ResponseEntity.ok(membresia);
    }

    @PostMapping
    public ResponseEntity<Membresia> createMembresia(@Valid @RequestBody Membresia membresia) {
        Membresia m = membresiaService.save(membresia);
        return ResponseEntity.status(HttpStatus.CREATED).body(m);
    }

    @PutMapping("{id}")
    public ResponseEntity<Membresia> updateMembresia(@PathVariable Integer id, @Valid @RequestBody Membresia membresia) {
        Membresia updatedMembresia = membresiaService.update(id, membresia);
        return ResponseEntity.ok(updatedMembresia);
    }

//    @DeleteMapping("{id}")
//    public ResponseEntity<Void> deleteMembresia(@PathVariable Integer id) {
//        membresiaService.delete(id);
//        return ResponseEntity.noContent().build();
//    }
}
