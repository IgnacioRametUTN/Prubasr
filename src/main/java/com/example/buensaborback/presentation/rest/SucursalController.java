package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.bussines.service.ISucursalService;
import com.example.buensaborback.domain.entities.Sucursal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/sucursales")
public class SucursalController {

    @Autowired
    private ISucursalService ISucursalService;

    @PostMapping
    public ResponseEntity<Sucursal> createSucursal(@RequestBody Sucursal sucursal) {
        Sucursal savedSucursal = ISucursalService.saveSucursal(sucursal);
        return ResponseEntity.ok(savedSucursal);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> getSucursalById(@PathVariable Long id) {
        Sucursal sucursal = ISucursalService.getSucursalById(id);
        if (sucursal != null) {
            return ResponseEntity.ok(sucursal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Sucursal>> getAllSucursales() {
        List<Sucursal> sucursales = ISucursalService.getAllSucursales();
        return ResponseEntity.ok(sucursales);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sucursal> updateSucursal(@PathVariable Long id, @RequestBody Sucursal sucursal) {
        Sucursal updatedSucursal = ISucursalService.updateSucursal(id, sucursal);
        if (updatedSucursal != null) {
            return ResponseEntity.ok(updatedSucursal);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSucursal(@PathVariable Long id) {
        ISucursalService.deleteSucursal(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<Sucursal>> getSucursalesByEmpresaId(@PathVariable Long empresaId) {
        List<Sucursal> sucursales = ISucursalService.getSucursalesByEmpresaId(empresaId);
        return ResponseEntity.ok(sucursales);
    }
}
