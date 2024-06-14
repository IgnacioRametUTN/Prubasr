package com.example.buensaborback.bussines.service;

import com.example.buensaborback.domain.entities.Categoria;

import java.util.List;

public interface ICategoriaService {
    Categoria getCategoriaById(Long id);
    boolean existsCategoriaById(Long id);
    boolean existsCategoriaByDenominacion(String denominacion);
    List<Categoria> findAll();
    List<Categoria> findAllCategoriasPadre();
    List<Categoria> findAllAlta();
    Categoria update(Long id, Categoria body);
    Categoria create(Long idPadre, Categoria body);
    Categoria delete(Long id);

}