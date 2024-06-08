package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.domain.entities.PromocionDetalle;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import com.example.buensaborback.repositories.PromocionDetalleRepository;
import org.springframework.stereotype.Service;

@Service
public class PromocionDetalleService {
    private final PromocionDetalleRepository promocionDetalleRepository;

    public PromocionDetalleService(PromocionDetalleRepository promocionDetalleRepository) {
        this.promocionDetalleRepository = promocionDetalleRepository;
    }

    public PromocionDetalle getPromocionDetalleById(Long id){
        return this.promocionDetalleRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Promocion Detalle con ID %d no encontrado", id)));
    }

    public boolean existsPromocionDetalleById(Long id){
        return this.promocionDetalleRepository.existsById(id);
    }
}
