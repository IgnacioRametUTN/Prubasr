package com.example.buensaborback.bussines.service;

import com.example.buensaborback.domain.entities.Pais;

import java.util.List;
import java.util.Optional;

public interface IPaisService {

    List<Pais> findAllPais();
    Pais getPaisById(Long idPais);
    boolean existPaisById(Long idPais);
}
