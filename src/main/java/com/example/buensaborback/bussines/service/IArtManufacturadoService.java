package com.example.buensaborback.bussines.service;

import com.example.buensaborback.domain.entities.*;
import java.util.List;
import java.util.Optional;

public interface IArtManufacturadoService {
     ArticuloManufacturado getArticuloManufacturadoById(Long id);
     boolean existsArticuloManufacturadoById(Long id);
     ArticuloManufacturado create(ArticuloManufacturado entity);
     ArticuloManufacturado update(Long id, ArticuloManufacturado entity);
     List<ArticuloManufacturado> getAll(Optional<Long> categoriaOpt, Optional<Long> unidadMedidaOpt, Optional<String> searchOpt);
     List<ArticuloManufacturado> getAll();

     ArticuloManufacturado delete(Long id);
}
