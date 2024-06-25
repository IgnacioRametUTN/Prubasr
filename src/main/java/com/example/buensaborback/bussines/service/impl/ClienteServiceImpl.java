package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.IClienteService;
import com.example.buensaborback.domain.entities.Cliente;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import com.example.buensaborback.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements IClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    public Cliente getClienteById(Long id){
        return this.clienteRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Cliente con ID %d no encontrado", id)));
    }

    public boolean existsClienteById(Long id){
        return this.clienteRepository.existsById(id);
    }

    @Override
    public Cliente create(Cliente entity) {
        System.out.println(entity);
        return this.clienteRepository.save(entity);
    }

    @Override
    public Cliente update(Long id, Cliente entity) {
        this.getClienteById(id);
        return this.clienteRepository.save(entity);
    }

    @Override
    public List<Cliente> getAll() {
        return this.clienteRepository.findAll();
    }

    @Override
    public Cliente delete(Long id) {
        Cliente cliente = this.getClienteById(id);
        cliente.setAlta(!cliente.isAlta());
        return this.clienteRepository.save(cliente);
    }

    @Override
    public List<Cliente> findClientes(String nombre, String apellido) {
        if (nombre != null && apellido != null) {
            return clienteRepository.findByNombreStartingWithIgnoreCaseAndApellidoStartingWithIgnoreCase(nombre, apellido);
        } else if (nombre != null) {
            return clienteRepository.findByNombreStartingWithIgnoreCase(nombre);
        } else if (apellido != null) {
            return clienteRepository.findByApellidoStartingWithIgnoreCase(apellido);
        } else {
            return clienteRepository.findAll();
        }
    }
}
