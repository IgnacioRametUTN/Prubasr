package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.bussines.facade.impl.UnidadMedidaFacadeImpl;
import com.example.buensaborback.domain.dtos.unidadmedida.UnidadMedidaDto;
import com.example.buensaborback.domain.entities.UnidadMedida;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnidadMedidaController  extends GenericControllerImpl<UnidadMedida, UnidadMedidaDto,Long, UnidadMedidaFacadeImpl> {
    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    public UnidadMedidaController(UnidadMedidaFacadeImpl facade) {
        super(facade);
    }
}
