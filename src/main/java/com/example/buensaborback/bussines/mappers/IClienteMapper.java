package com.example.buensaborback.bussines.mappers;

import com.example.buensaborback.domain.dtos.cliente.ClienteFullDto;
import com.example.buensaborback.domain.entities.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring", uses = IDomicilioMapper.class)
public interface IClienteMapper extends IBaseMapper<Cliente, ClienteFullDto> {

    @Mapping(target = "imagen", source = "source.imagen.url")
    ClienteFullDto toDTO(Cliente source);

    @Mapping(target = "imagen.url", source = "source.imagen")
    Cliente toEntity(ClienteFullDto source);
    List<ClienteFullDto> toDTOsList(List<Cliente> source);
    List<Cliente> toEntitiesList(List<ClienteFullDto> source);
}