package com.example.buensaborback.bussines.facade;


import com.example.buensaborback.domain.dtos.articulos.insumo.ArticuloInsumoDto;

import java.util.List;
import java.util.Optional;

public interface IArticuloInsumoFacade  extends IBaseFacade<ArticuloInsumoDto, Long> {

    List<ArticuloInsumoDto> getArticulosInsumos(Optional<Long> categoria, Optional<Long> unidadMedida, Optional<String> denominacion);
}
