package com.example.buensaborback.bussines.mappers;

import com.example.buensaborback.domain.dtos.domicilio.DomicilioFullDto;
import com.example.buensaborback.domain.dtos.domicilio.DomicilioShortDto;
import com.example.buensaborback.domain.entities.Domicilio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IDomicilioMapper extends IBaseMapper<Domicilio, DomicilioFullDto> {



    @Override
    @Mapping( target = "localidad" ,source = "localidad.nombre")
    @Mapping( target = "provincia" ,source = "localidad.provincia.nombre")
    @Mapping( target = "pais" ,source = "localidad.provincia.pais.nombre")
    DomicilioFullDto toDTO(Domicilio source);

    @Override
    @Mapping( source = "localidad" ,target = "localidad.nombre")
    @Mapping( source = "provincia" ,target = "localidad.provincia.nombre")
    @Mapping( source = "pais" ,target = "localidad.provincia.pais.nombre")
    Domicilio toEntity(DomicilioFullDto source);

    @Override
    List<DomicilioFullDto> toDTOsList(List<Domicilio> source);

    @Override
    List<Domicilio> toEntitiesList(List<DomicilioFullDto> source);
}
