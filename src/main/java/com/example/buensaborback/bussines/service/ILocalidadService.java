package com.example.buensaborback.bussines.service;

import com.example.buensaborback.domain.entities.Localidad;

import java.util.List;

public interface ILocalidadService {
    List<Localidad> findLocalidadesByProvincia(Long idProvincia);
    Localidad getLocalidadById(Long idLocalidad);
    boolean existLocalidadById(Long idLocalidad);
}
