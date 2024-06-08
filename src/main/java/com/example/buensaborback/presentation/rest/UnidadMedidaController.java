package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.bussines.service.impl.UnidadMedidaServiceImpl;
import com.example.buensaborback.domain.entities.UnidadMedida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/unidades-medida")
@CrossOrigin("*")
public class UnidadMedidaController  {
    private final UnidadMedidaServiceImpl unidadMedidaService;

    @Autowired
    public UnidadMedidaController(UnidadMedidaServiceImpl unidadMedidaService) {
        this.unidadMedidaService = unidadMedidaService;
    }

    @GetMapping("")
    public ResponseEntity<List<UnidadMedida>>getAll(){
        return ResponseEntity.ok().body(this.unidadMedidaService.findAll());
    }

    @GetMapping("/alta")
    public ResponseEntity<List<UnidadMedida>> getAllAlta(){
        return ResponseEntity.ok().body(this.unidadMedidaService.findAllAlta());
    }


    @GetMapping("/{id}")
    public ResponseEntity<UnidadMedida> getOne(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(this.unidadMedidaService.getUnidadMedidaById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadMedida> update(@PathVariable("id") Long id, @RequestBody UnidadMedida body){
        return ResponseEntity.ok().body(this.unidadMedidaService.update(id, body));
    }

    @PostMapping("")
    public ResponseEntity<UnidadMedida> save(@RequestBody UnidadMedida body){
        return ResponseEntity.ok().body(this.unidadMedidaService.create(body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UnidadMedida> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(this.unidadMedidaService.delete(id));
    }

}
