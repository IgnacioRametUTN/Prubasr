package com.example.buensaborback.bussines.mappers;

import com.example.buensaborback.domain.dtos.cliente.ClienteShortDto;
import com.example.buensaborback.domain.entities.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IClienteMapper {

    ClienteShortDto clienteToClienteShortDto(Cliente cliente);
}
