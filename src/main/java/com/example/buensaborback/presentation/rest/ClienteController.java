package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.bussines.facade.impl.ClienteFacadeImpl;
import com.example.buensaborback.domain.dtos.cliente.ClienteFullDto;
import com.example.buensaborback.domain.entities.Cliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin("*")
public class ClienteController extends GenericControllerImpl<Cliente, ClienteFullDto,Long, ClienteFacadeImpl> {
    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);
    public ClienteController(ClienteFacadeImpl facade) {
        super(facade);
    }

}
