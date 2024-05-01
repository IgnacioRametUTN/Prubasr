package com.example.buensaborback.bussines.mappers;

import com.example.buensaborback.domain.dtos.domicilio.DomicilioFullDto;
import com.example.buensaborback.domain.dtos.domicilio.DomicilioShortDto;
import com.example.buensaborback.domain.entities.Domicilio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface IDomicilioMapper {



    @Mapping(target = "localidad" , source = "domicilio.localidad.nombre")
    DomicilioShortDto toDomicilioShortDto(Domicilio domicilio);
}
