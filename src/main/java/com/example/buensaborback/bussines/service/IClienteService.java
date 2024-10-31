package com.example.buensaborback.bussines.service;

import com.example.buensaborback.domain.entities.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteService {

    Cliente getClienteById(Long id);
    boolean existsClienteById(Long id);
    Cliente create(Cliente entity);
    Cliente update(Long id, Cliente entity);
    List<Cliente> getAll();
    Cliente delete(Long id);

    List<Cliente>findClientes(String nombre,String apellido);

    Cliente getClienteByUsername(String username);
}
