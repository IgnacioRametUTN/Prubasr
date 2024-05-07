package com.example.buensaborback.bussines.facade.impl;

import com.example.buensaborback.bussines.facade.IClienteFacade;
import com.example.buensaborback.bussines.mappers.IBaseMapper;
import com.example.buensaborback.bussines.mappers.IClienteMapper;
import com.example.buensaborback.bussines.service.impl.ClienteServiceImpl;
import com.example.buensaborback.domain.dtos.cliente.ClienteFullDto;
import com.example.buensaborback.domain.entities.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteFacadeImpl extends BaseFacadeImpl<Cliente, ClienteFullDto, Long> implements IClienteFacade {

    public ClienteFacadeImpl(ClienteServiceImpl baseService, IClienteMapper baseMapper) {
        super(baseService, baseMapper);
    }



}
