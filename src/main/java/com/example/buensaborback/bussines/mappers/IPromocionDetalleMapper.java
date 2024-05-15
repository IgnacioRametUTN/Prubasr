package com.example.buensaborback.bussines.mappers;

import com.example.buensaborback.domain.dtos.promocion.PromocionDetalleDto;
import com.example.buensaborback.domain.entities.PromocionDetalle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingInheritanceStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper(componentModel = "spring", mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG)
public interface IPromocionDetalleMapper extends IBaseMapper<PromocionDetalle, PromocionDetalleDto> {
    @Override
    PromocionDetalleDto toDTO(PromocionDetalle promocionDetalle);

    @Override
    PromocionDetalle toEntity(PromocionDetalleDto promocionDetalleDto);

    String map(PromocionDetalleDto value);
    @Override
    List<PromocionDetalleDto> toDTOsList(List<PromocionDetalle> source);

    @Override
    List<PromocionDetalle> toEntitiesList(List<PromocionDetalleDto> source);
}
