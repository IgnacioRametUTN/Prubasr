package com.example.buensaborback.bussines.mappers;

import com.example.buensaborback.domain.dtos.articulos.insumo.ArticuloInsumoDto;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingInheritanceStrategy;

import java.util.List;

@Mapper(componentModel = "spring", mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG,
        uses = IPromocionDetalleMapper.class)
public interface IArticuloInsumoMapper extends IBaseMapper<ArticuloInsumo, ArticuloInsumoDto> {
    @Override
    ArticuloInsumoDto toDTO(ArticuloInsumo source);

    @Override
    ArticuloInsumo toEntity(ArticuloInsumoDto source);

    @Override
    List<ArticuloInsumoDto> toDTOsList(List<ArticuloInsumo> source);

    @Override
    List<ArticuloInsumo> toEntitiesList(List<ArticuloInsumoDto> source);
}
