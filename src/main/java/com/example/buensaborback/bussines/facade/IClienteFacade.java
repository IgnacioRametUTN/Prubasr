package com.example.buensaborback.bussines.facade;

import com.example.buensaborback.domain.dtos.cliente.ClienteShortDto;

import java.util.List;

public interface IClienteFacade {

    List<ClienteShortDto> getAllClienteShortDto();
}
