package com.sgdc.core.ws.controller;

import com.sgdc.core.ws.model.Beneficio;
import com.sgdc.core.ws.service.BeneficioService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/beneficios")
public class BeneficioController {

    private static final Logger log = LoggerFactory.getLogger(BeneficioController.class);

    private final BeneficioService beneficioService;

    public BeneficioController(BeneficioService beneficioService) {
        this.beneficioService = beneficioService;
    }

    @GetMapping
    public ResponseEntity<List<Beneficio>> inicio(@RequestParam(value = "q", required = false) String keyword, Model model) {
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
