package com.example.buensaborback.bussines.facade.impl;

import com.example.buensaborback.bussines.facade.IClienteFacade;
import com.example.buensaborback.bussines.mappers.IClienteMapper;
import com.example.buensaborback.bussines.service.IClienteService;
import com.example.buensaborback.domain.dtos.cliente.ClienteShortDto;
import com.example.buensaborback.domain.entities.Cliente;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteFacadeImpl implements IClienteFacade {

    private final IClienteMapper clienteMapper;
    private final IClienteService clienteService;

    public ClienteFacadeImpl(IClienteMapper clienteMapper, IClienteService clienteService) {
        this.clienteMapper = clienteMapper;
        this.clienteService = clienteService;
    }

    @Override
    public List<ClienteShortDto> getAllClienteShortDto() {
        List<Cliente> clientes = clienteService.getAll();

        return clientes.stream().map(cliente -> clienteMapper.clienteToClienteShortDto(cliente)).toList();
    }
}
