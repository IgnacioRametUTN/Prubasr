package com.example.buensaborback.bussines.service;

import com.example.buensaborback.domain.entities.PromocionDetalle;

import java.util.List;

public interface IPromocionDetalleService {
    PromocionDetalle getPromocionDetalleById(Long id);
    boolean existsPromocionDetalleById(Long id);
    PromocionDetalle create(PromocionDetalle entity);
    PromocionDetalle update(Long id, PromocionDetalle entity);
    List<PromocionDetalle> getAll();
    PromocionDetalle delete(Long id);
}
