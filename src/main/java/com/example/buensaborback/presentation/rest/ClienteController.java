package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.bussines.facade.IClienteFacade;
import com.example.buensaborback.bussines.service.IClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin("*")
public class ClienteController {
    private IClienteFacade clienteFacade;

    public ClienteController(IClienteFacade clienteFacade) {
        this.clienteFacade = clienteFacade;
    }

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok().body(clienteFacade.getAllClienteShortDto());
    }
}
