package com.example.buensaborback.bussines.service;

import com.example.buensaborback.domain.entities.Promocion;

import java.util.List;

public interface IPromocionService {
    Promocion getPromocionById(Long id);
    boolean existsPromocionById(Long id);
    Promocion create(Promocion entity);
    Promocion update(Long id, Promocion entity);
    List<Promocion> getAll();
    Promocion delete(Long id);
}
