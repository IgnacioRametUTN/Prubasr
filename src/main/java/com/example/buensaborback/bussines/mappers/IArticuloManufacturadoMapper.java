package com.example.buensaborback.bussines.mappers;

import com.example.buensaborback.domain.dtos.articulos.manufacturado.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.dtos.cliente.ClienteFullDto;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.domain.entities.Cliente;
import com.example.buensaborback.domain.entities.UnidadMedida;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingInheritanceStrategy;

import java.util.List;

@Mapper(componentModel = "spring", mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG,
uses = {IPromocionDetalleMapper.class, IArticuloInsumoMapper.class} )
public interface IArticuloManufacturadoMapper extends IBaseMapper<ArticuloManufacturado, ArticuloManufacturadoDto> {

    @Override
    //@Mapping(source = "source.unidadMedida.denominacion", target = "unidadMedida")
    ArticuloManufacturadoDto toDTO(ArticuloManufacturado source);

    @Override
   // @Mapping(target = "unidadMedida.denominacion", source = "unidadMedida")
    ArticuloManufacturado toEntity(ArticuloManufacturadoDto source);

    @Override
    List<ArticuloManufacturadoDto> toDTOsList(List<ArticuloManufacturado> source);

    @Override
    List<ArticuloManufacturado> toEntitiesList(List<ArticuloManufacturadoDto> source);
}
