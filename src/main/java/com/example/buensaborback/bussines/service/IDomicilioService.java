package com.example.buensaborback.bussines.service;

import com.example.buensaborback.domain.entities.*;

import java.util.List;

public interface IDomicilioService {

    Domicilio getDomicilioById(Long id);
    boolean existsDomicilioById(Long id);
    Domicilio update(Long id, Domicilio body);
    Domicilio create(Domicilio body);
    Domicilio delete(Long id);
    List<Domicilio> findAll() ;
    List<Localidad> findAllLocalidad();
    List<Provincia> findAllProvincia();
    List<Domicilio> findAllAlta();

    List<Pais> findAllPais();

    List<Provincia> findAllProvinciaByPais(Long id);
    List<Localidad> findAllLocalidadByProvincia(Long idProvincia);
}
