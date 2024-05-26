package com.example.buensaborback.bussines.service;

import com.example.buensaborback.domain.entities.ArticuloInsumo;

import java.util.List;
import java.util.Optional;

public interface IArticuloInsumoService  extends  IBaseService<ArticuloInsumo, Long >{

    List<ArticuloInsumo> getAll(Optional<Long> categoria, Optional<Long> unidadMedida, Optional<String> denominacion);
}
