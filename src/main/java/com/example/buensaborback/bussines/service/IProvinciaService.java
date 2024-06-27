package com.example.buensaborback.bussines.service;

import com.example.buensaborback.domain.entities.Provincia;
import com.example.buensaborback.domain.entities.Provincia;

import java.util.List;

public interface IProvinciaService {

    List<Provincia> findProvinciasByPais(Long id);
    Provincia getProvinciaById(Long idProvincia);
    boolean existProvinciaById(Long idProvincia);
}
