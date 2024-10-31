package com.example.buensaborback.bussines.service;

import com.example.buensaborback.domain.entities.UnidadMedida;
import com.example.buensaborback.presentation.advice.exception.DuplicateEntryException;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;

import java.util.List;

public interface IUnidadMedidaService {
     UnidadMedida getUnidadMedidaById(Long id);
     boolean existsUnidadMedidaById(Long id);
     boolean existsUnidadMedidaByDenominacion(String denominacion);
     UnidadMedida update(Long id, UnidadMedida body);
     UnidadMedida create(UnidadMedida body);
     UnidadMedida delete(Long id);
     List<UnidadMedida> findAll() ;
     List<UnidadMedida> findAllAlta();

}
