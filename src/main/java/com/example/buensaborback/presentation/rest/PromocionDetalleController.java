package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.domain.entities.PromocionDetalle;
import com.example.buensaborback.bussines.service.IPromocionDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/promociones-detalles")
public class PromocionDetalleController {

    @Autowired
    private IPromocionDetalleService promocionDetalleService;

    @GetMapping
    public List<PromocionDetalle> getAllPromocionesDetalles() {
        return promocionDetalleService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PromocionDetalle> getPromocionDetalleById(@PathVariable Long id) {
        PromocionDetalle promocionDetalle = promocionDetalleService.getPromocionDetalleById(id);
        return promocionDetalle != null ? ResponseEntity.ok(promocionDetalle) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public PromocionDetalle createPromocionDetalle(@RequestBody PromocionDetalle promocionDetalle) {
        return promocionDetalleService.create(promocionDetalle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PromocionDetalle> updatePromocionDetalle(@PathVariable Long id, @RequestBody PromocionDetalle promocionDetalleDetails) {
        PromocionDetalle updatedPromocionDetalle = promocionDetalleService.update(id, promocionDetalleDetails);
        return updatedPromocionDetalle != null ? ResponseEntity.ok(updatedPromocionDetalle) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PromocionDetalle> deletePromocionDetalle(@PathVariable Long id) {
        PromocionDetalle deletedPromocionDetalle = promocionDetalleService.delete(id);
        return deletedPromocionDetalle != null ? ResponseEntity.ok(deletedPromocionDetalle) : ResponseEntity.notFound().build();
    }
}
