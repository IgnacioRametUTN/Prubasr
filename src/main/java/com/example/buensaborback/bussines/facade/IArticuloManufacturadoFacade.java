package com.example.buensaborback.bussines.facade;

import com.example.buensaborback.domain.dtos.articulos.manufacturado.ArticuloManufacturadoDto;

import java.util.List;
import java.util.Optional;

public interface IArticuloManufacturadoFacade extends IBaseFacade<ArticuloManufacturadoDto, Long> {
    List<ArticuloManufacturadoDto> getArticulosInsumos(Optional<Long> categoria, Optional<Long> unidadMedida, Optional<String> denominacion);
}
