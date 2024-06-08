package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.bussines.facade.impl.EmpresaFacadeImpl;
import com.example.buensaborback.domain.dtos.empresa.EmpresaDto;
import com.example.buensaborback.domain.entities.Empresa;
import com.example.buensaborback.bussines.service.impl.EmpresaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/empresas")
@CrossOrigin("*")
public class EmpresaController  {

    @Autowired
    private final EmpresaServiceImpl empresaService;

    public EmpresaController(EmpresaServiceImpl empresaService) {
        this.empresaService = empresaService;
    }
}
