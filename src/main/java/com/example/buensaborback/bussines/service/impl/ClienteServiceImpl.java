package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.IClienteService;
import com.example.buensaborback.domain.entities.Cliente;
import com.example.buensaborback.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements IClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Cliente> getAll() {
        return this.clienteRepository.findAll();
    }
}
