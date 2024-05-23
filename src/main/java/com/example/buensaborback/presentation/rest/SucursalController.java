package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.bussines.service.impl.EmpresaServiceImpl;
import com.example.buensaborback.bussines.service.impl.SucursalServiceImpl;
import com.example.buensaborback.domain.entities.Empresa;
import com.example.buensaborback.domain.entities.Sucursal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sucursales")
public class SucursalController {
    @Autowired
    private SucursalServiceImpl sucursalService;

    @GetMapping
    public List<Sucursal> getAll() {
        return sucursalService.findAll();
    }

    @PostMapping
    public Sucursal create(@RequestBody Sucursal sucursal) {
        return sucursalService.save(sucursal);
    }

}
