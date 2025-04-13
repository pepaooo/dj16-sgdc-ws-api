package com.sgdc.core.ws.controller;

import com.sgdc.core.ws.model.Beneficio;
import com.sgdc.core.ws.service.BeneficioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/beneficios")
public class BeneficioController {

    private final BeneficioService beneficioService;

    public BeneficioController(BeneficioService beneficioService) {
        this.beneficioService = beneficioService;
    }

    @GetMapping
    public ResponseEntity<List<Beneficio>> findAll(@RequestParam(value = "q", required = false) String keyword, Model model) {
        List<Beneficio> beneficios = beneficioService.search(keyword);
        return ResponseEntity.ok(beneficios);
    }

    @GetMapping("{id}")
    public ResponseEntity<Beneficio> getBeneficio(@PathVariable Integer id) {
        Beneficio beneficio = beneficioService.findById(id);
        return ResponseEntity.ok(beneficio);
    }

    @PostMapping
    public ResponseEntity<Beneficio> createBeneficio(@Valid @RequestBody Beneficio beneficio) {
        Beneficio beneficioCreated = beneficioService.save(beneficio);
        return ResponseEntity.status(HttpStatus.CREATED).body(beneficioCreated);
    }

    @PutMapping("{id}")
    public ResponseEntity<Beneficio> updateBeneficio(@PathVariable Integer id, @Valid @RequestBody Beneficio beneficio) {
        Beneficio updatedBeneficio = beneficioService.update(id, beneficio);
        return ResponseEntity.ok(updatedBeneficio);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBeneficio(@PathVariable Integer id) {
        beneficioService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
