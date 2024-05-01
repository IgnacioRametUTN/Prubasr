package com.example.buensaborback.bussines.service;

import com.example.buensaborback.domain.dtos.cliente.ClienteFullDto;
import com.example.buensaborback.domain.dtos.cliente.ClienteShortDto;
import com.example.buensaborback.domain.entities.Cliente;

import java.util.List;

public interface IClienteService {
    List<Cliente> getAll();
}
