package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.bussines.facade.impl.CategoriaFacadeImpl;
import com.example.buensaborback.domain.dtos.categoria.CategoriaDto;
import com.example.buensaborback.domain.entities.Categoria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin("*")
public class CategoriaController extends GenericControllerImpl<Categoria, CategoriaDto,Long, CategoriaFacadeImpl> {
    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    public CategoriaController(CategoriaFacadeImpl facade) {
        super(facade);
    }
}
    