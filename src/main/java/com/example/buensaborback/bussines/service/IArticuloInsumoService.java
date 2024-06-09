package com.example.buensaborback.bussines.service;

import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.domain.entities.Categoria;
import com.example.buensaborback.domain.entities.PromocionDetalle;
import com.example.buensaborback.domain.entities.UnidadMedida;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface IArticuloInsumoService {
     ArticuloInsumo getArticuloInsumoById(Long id);
     boolean existsArticuloInsumoById(Long id);
     ArticuloInsumo create(ArticuloInsumo entity);
     ArticuloInsumo update(Long id,ArticuloInsumo entity);
     ArticuloInsumo delete(Long id);
     List<ArticuloInsumo> getAll();
     List<ArticuloInsumo> getAll(Optional<Long> categoriaOpt, Optional<Long> unidadMedidaOpt, Optional<String> searchOpt);
}
