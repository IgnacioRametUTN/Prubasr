package com.example.buensaborback.bussines.mappers;

import com.example.buensaborback.domain.dtos.BaseDto;
import com.example.buensaborback.domain.dtos.cliente.ClienteFullDto;
import com.example.buensaborback.domain.entities.Base;
import com.example.buensaborback.domain.entities.Cliente;
import org.mapstruct.*;

import java.util.List;


@Mapper(componentModel = "spring", uses = IDomicilioMapper.class, mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG)
public interface IClienteMapper extends IBaseMapper<Cliente, ClienteFullDto> {

    @Mapping(target = "imagen", source = "source.imagen.url")
    ClienteFullDto toDTO(Cliente source);

    @Mapping(target = "imagen.url", source = "source.imagen")
    Cliente toEntity(ClienteFullDto source);
    List<ClienteFullDto> toDTOsList(List<Cliente> source);
    List<Cliente> toEntitiesList(List<ClienteFullDto> source);
}