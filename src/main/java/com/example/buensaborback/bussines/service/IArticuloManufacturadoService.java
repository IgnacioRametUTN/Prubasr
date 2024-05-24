package com.example.buensaborback.bussines.service;

import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.domain.entities.Cliente;
import com.example.buensaborback.domain.entities.Promocion;

import java.util.List;
import java.util.Optional;

public interface IArticuloManufacturadoService extends  IBaseService<ArticuloManufacturado, Long >{

    List<ArticuloManufacturado> getAll(Optional<Long> categoria, Optional<Long> unidadMedida, Optional<String> denominacion);
}
