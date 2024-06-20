package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.domain.entities.Promocion;
import com.example.buensaborback.bussines.service.IPromocionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/promociones")
public class PromocionController {

    @Autowired
    private IPromocionService promocionService;

    @GetMapping
    public List<Promocion> getAllPromociones() {
        return promocionService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Promocion> getPromocionById(@PathVariable Long id) {
        Promocion promocion = promocionService.getPromocionById(id);
        return promocion != null ? ResponseEntity.ok(promocion) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Promocion createPromocion(@RequestBody Promocion promocion) {
        return promocionService.create(promocion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Promocion> updatePromocion(@PathVariable Long id, @RequestBody Promocion promocionDetails) {
        Promocion updatedPromocion = promocionService.update(id, promocionDetails);
        return updatedPromocion != null ? ResponseEntity.ok(updatedPromocion) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Promocion> deletePromocion(@PathVariable Long id) {
        return ResponseEntity.ok(promocionService.delete(id));
    }
}
