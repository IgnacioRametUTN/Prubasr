package com.example.buensaborback.bussines.mappers;

import com.example.buensaborback.domain.dtos.domicilio.DomicilioFullDto;
import com.example.buensaborback.domain.dtos.domicilio.DomicilioShortDto;
import com.example.buensaborback.domain.entities.Domicilio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IDomicilioMapper {

    @Mapping( target = "localidad" ,source = "localidad.nombre")
    @Mapping( target = "provincia" ,source = "localidad.provincia.nombre")
    @Mapping( target = "pais" ,source = "localidad.provincia.pais.nombre")
    DomicilioFullDto domicilioToDomicilioFullDto(Domicilio domicilio);

    @Mapping( source = "localidad" ,target = "localidad.nombre")
    @Mapping( source = "provincia" ,target = "localidad.provincia.nombre")
    @Mapping( source = "pais" ,target = "localidad.provincia.pais.nombre")
    Domicilio DomicilioFullDtoToDomicilio(DomicilioFullDto DomicilioFullDto);

    List<DomicilioFullDto> domiciliosToDomicilioFullDtos(List<Domicilio> domicilios);

    List<Domicilio> DomicilioFullDtosToDomicilios(List<DomicilioFullDto> DomicilioFullDtos);
}
