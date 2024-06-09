package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.bussines.service.IArticuloInsumoService;
import com.example.buensaborback.bussines.service.impl.ArticuloInsumoServiceImpl;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/articulos/insumos")
@CrossOrigin("*")
public class ArticuloInsumoController {

    private final IArticuloInsumoService articuloInsumoService;

    @Autowired
    public ArticuloInsumoController(ArticuloInsumoServiceImpl articuloInsumoService) {
        this.articuloInsumoService = articuloInsumoService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok().body(this.articuloInsumoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(this.articuloInsumoService.getArticuloInsumoById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody ArticuloInsumo body){
        return ResponseEntity.ok().body(this.articuloInsumoService.create(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ArticuloInsumo body){
        return ResponseEntity.ok().body(this.articuloInsumoService.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(this.articuloInsumoService.delete(id));
    }


    @GetMapping("/search")
    public ResponseEntity<?> getAll(@RequestParam("categoria_id") Optional<Long> categoria,
                                    @RequestParam("unidad_id") Optional<Long> unidadMedida,
                                    @RequestParam("denominacion") Optional<String> denominacion){
        return ResponseEntity.ok().body(this.articuloInsumoService.getAll(categoria, unidadMedida, denominacion));
    }


}
    