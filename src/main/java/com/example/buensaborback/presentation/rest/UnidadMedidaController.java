package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.bussines.facade.impl.UnidadMedidaFacadeImpl;
import com.example.buensaborback.bussines.service.impl.UnidadMedidaServiceImpl;
import com.example.buensaborback.domain.dtos.unidadmedida.UnidadMedidaDto;
import com.example.buensaborback.domain.entities.UnidadMedida;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/unidades-medida")
@CrossOrigin("*")
public class UnidadMedidaController  {

    private final UnidadMedidaServiceImpl unidadMedidaService;
    @Autowired
    public UnidadMedidaController(UnidadMedidaServiceImpl unidadMedidaService) {
        this.unidadMedidaService = unidadMedidaService;
    }
}
