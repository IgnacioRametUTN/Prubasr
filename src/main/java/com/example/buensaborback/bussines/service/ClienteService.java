package com.example.buensaborback.bussines.service;

import com.example.buensaborback.domain.entities.Cliente;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import com.example.buensaborback.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    public Cliente getClienteById(Long id){
        return this.clienteRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Cliente con ID %d no encontrado", id)));
    }

    public boolean existsClienteById(Long id){
        return this.clienteRepository.existsById(id);
    }
}
