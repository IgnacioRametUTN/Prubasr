package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.bussines.facade.impl.ArticuloInsumoFacadeImpl;
import com.example.buensaborback.domain.dtos.articulos.insumo.ArticuloInsumoDto;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/articulos/insumos")
@CrossOrigin("*")
public class ArticuloInsumoController extends GenericControllerImpl<ArticuloInsumo, ArticuloInsumoDto,Long, ArticuloInsumoFacadeImpl> {
    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    public ArticuloInsumoController(ArticuloInsumoFacadeImpl facade) {
        super(facade);
    }
}
    