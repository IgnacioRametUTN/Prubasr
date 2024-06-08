package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.bussines.service.impl.DomicilioServiceImpl;
import com.example.buensaborback.domain.entities.Domicilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/domicilios")
@CrossOrigin("*")
public class DomicilioController {
    private final DomicilioServiceImpl domicilioService;

    @Autowired
    public DomicilioController(DomicilioServiceImpl DomicilioService) {
        this.domicilioService = DomicilioService;
    }

    @GetMapping("")
    public ResponseEntity<List<Domicilio>> getAll(){
        return ResponseEntity.ok().body(this.domicilioService.findAll());
    }

    @GetMapping("/alta")
    public ResponseEntity<List<Domicilio>> getAllAlta(){
        return ResponseEntity.ok().body(this.domicilioService.findAllAlta());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Domicilio> getOne(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(this.domicilioService.getDomicilioById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Domicilio> update(@PathVariable("id") Long id, @RequestBody Domicilio body){
        return ResponseEntity.ok().body(this.domicilioService.update(id, body));
    }

    @PostMapping("")
    public ResponseEntity<Domicilio> save(@RequestBody Domicilio body){
        return ResponseEntity.ok().body(this.domicilioService.create(body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Domicilio> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(this.domicilioService.delete(id));
    }
}
