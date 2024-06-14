package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.bussines.service.IClienteService;
import com.example.buensaborback.bussines.service.impl.ClienteServiceImpl;
import com.example.buensaborback.domain.entities.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin("*")
public class ClienteController{

    private final IClienteService clienteService;

    @Autowired
    public ClienteController(ClienteServiceImpl clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok().body(this.clienteService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(this.clienteService.getClienteById(id));
    }


    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Cliente body){
        return ResponseEntity.ok().body(this.clienteService.create(body));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody Cliente body){
        return ResponseEntity.ok().body(this.clienteService.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(this.clienteService.delete(id));
    }



}
