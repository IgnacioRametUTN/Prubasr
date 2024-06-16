package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.bussines.service.IArtManufacturadoService;
import com.example.buensaborback.bussines.service.impl.ArtManufacturadoServiceImpl;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/articulos/manufacturados")
@CrossOrigin("*")
public class ArticuloManufacturadoController{

    private final IArtManufacturadoService artManufacturadoService;

    @Autowired
    public ArticuloManufacturadoController(ArtManufacturadoServiceImpl artManufacturadoService) {
        this.artManufacturadoService = artManufacturadoService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok().body(this.artManufacturadoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(this.artManufacturadoService.getArticuloManufacturadoById(id));
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody ArticuloManufacturado body){
        return ResponseEntity.ok().body(this.artManufacturadoService.create(body));
    }

    @PostMapping("/bulk")
    public ResponseEntity<?> create(@RequestBody List<ArticuloManufacturado> body){
        body.forEach(this.artManufacturadoService::create);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ArticuloManufacturado body){
        return ResponseEntity.ok().body(this.artManufacturadoService.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(this.artManufacturadoService.delete(id));
    }


    @GetMapping("/search")
    public ResponseEntity<?> getAll(@RequestParam("categoria_id") Optional<Long> categoria,
                                    @RequestParam("unidad_id") Optional<Long> unidadMedida,
                                    @RequestParam("denominacion") Optional<String> denominacion){
        return ResponseEntity.ok().body(this.artManufacturadoService.getAll(categoria, unidadMedida, denominacion));
    }

}