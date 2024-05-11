package com.example.buensaborback.bussines.mappers;

import com.example.buensaborback.domain.dtos.promocion.PromocionDetalleDto;
import com.example.buensaborback.domain.entities.PromocionDetalle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingInheritanceStrategy;

import java.util.List;
@Mapper(componentModel = "spring", mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG)
public interface IPromocionDetalleMapper extends IBaseMapper<PromocionDetalle, PromocionDetalleDto> {
    @Override
    @Mapping(target = "articulo", source = "source.articulo.denominacion")
    PromocionDetalleDto toDTO(PromocionDetalle source);

    @Override
    @Mapping(source = "source", target = "articulo.denominacion")
    PromocionDetalle toEntity(PromocionDetalleDto source);

    String map(PromocionDetalleDto value);
    @Override
    List<PromocionDetalleDto> toDTOsList(List<PromocionDetalle> source);

    @Override
    List<PromocionDetalle> toEntitiesList(List<PromocionDetalleDto> source);
}
