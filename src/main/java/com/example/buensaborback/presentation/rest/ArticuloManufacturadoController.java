package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.bussines.facade.impl.ArticuloManufacturadoFacadeImpl;
import com.example.buensaborback.bussines.facade.impl.ClienteFacadeImpl;
import com.example.buensaborback.domain.dtos.articulos.manufacturado.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/articulos/manufacturados")
@CrossOrigin("*")
public class ArticuloManufacturadoController extends GenericControllerImpl<ArticuloManufacturado, ArticuloManufacturadoDto,Long, ArticuloManufacturadoFacadeImpl> {
    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);
    public ArticuloManufacturadoController(ArticuloManufacturadoFacadeImpl facade) {
        super(facade);
    }

}