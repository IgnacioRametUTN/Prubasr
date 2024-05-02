package com.example.buensaborback.bussines.mappers;

import com.example.buensaborback.domain.dtos.cliente.ClienteBase;
import com.example.buensaborback.domain.entities.Cliente;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface IClienteMapper extends IBaseMapper<Cliente, ClienteBase> {

    ClienteBase toDTO(Cliente source);
    Cliente toEntity(ClienteBase source);
    List<ClienteBase> toDTOsList(List<Cliente> source);
    List<Cliente> toEntitiesList(List<ClienteBase> source);
}