package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.domain.entities.Empresa;
import com.example.buensaborback.bussines.service.impl.EmpresaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {
    @Autowired
    private EmpresaServiceImpl empresaService;

    @GetMapping
    public List<Empresa> getAll() {
        return empresaService.findAll();
    }

    @PostMapping
    public Empresa create(@RequestBody Empresa empresa) {
        return empresaService.save(empresa);
    }

    // Otros métodos CRUD si es necesario
}
