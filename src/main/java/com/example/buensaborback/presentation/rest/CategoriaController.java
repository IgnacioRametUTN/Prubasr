package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.bussines.facade.impl.CategoriaFacadeImpl;
import com.example.buensaborback.bussines.service.impl.CategoriaServiceImpl;
import com.example.buensaborback.domain.dtos.categoria.CategoriaDto;
import com.example.buensaborback.domain.entities.Categoria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin("*")
public class CategoriaController{

    private final CategoriaServiceImpl categoriaService;
    @Autowired
    public CategoriaController(CategoriaServiceImpl categoriaService) {
        this.categoriaService = categoriaService;
    }
}
    