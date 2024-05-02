package com.example.buensaborback.bussines.facade.impl;

import com.example.buensaborback.bussines.facade.IClienteFacade;
import com.example.buensaborback.bussines.mappers.IBaseMapper;
import com.example.buensaborback.bussines.mappers.IClienteMapper;
import com.example.buensaborback.bussines.service.IBaseService;
import com.example.buensaborback.bussines.service.IClienteService;
import com.example.buensaborback.bussines.service.impl.BaseServiceImpl;
import com.example.buensaborback.bussines.service.impl.ClienteServiceImpl;
import com.example.buensaborback.domain.dtos.cliente.ClienteBase;
import com.example.buensaborback.domain.entities.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteFacadeImpl extends BaseFacadeImpl<Cliente, ClienteBase, Long> implements IClienteFacade {

    public ClienteFacadeImpl(ClienteServiceImpl baseService, IBaseMapper<Cliente, ClienteBase> baseMapper) {
        super(baseService, baseMapper);
    }



}
