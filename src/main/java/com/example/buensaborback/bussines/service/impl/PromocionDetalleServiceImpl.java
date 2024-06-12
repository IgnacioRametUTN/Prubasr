package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.IPromocionDetalleService;
import com.example.buensaborback.domain.entities.PromocionDetalle;
import com.example.buensaborback.repositories.PromocionDetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromocionDetalleServiceImpl implements IPromocionDetalleService {

    @Autowired
    private PromocionDetalleRepository promocionDetalleRepository;

    @Override
    public PromocionDetalle getPromocionDetalleById(Long id) {
        return promocionDetalleRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existsPromocionDetalleById(Long id) {
        return promocionDetalleRepository.existsById(id);
    }

    @Override
    public PromocionDetalle create(PromocionDetalle entity) {
        return promocionDetalleRepository.save(entity);
    }

    @Override
    public PromocionDetalle update(Long id, PromocionDetalle entity) {
        if (existsPromocionDetalleById(id)) {
            entity.setId(id);
            return promocionDetalleRepository.save(entity);
        }
        return null;
    }

    @Override
    public List<PromocionDetalle> getAll() {
        return promocionDetalleRepository.findAll();
    }

    @Override
    public PromocionDetalle delete(Long id) {
        Optional<PromocionDetalle> promocionDetalleOpt = promocionDetalleRepository.findById(id);
        if (promocionDetalleOpt.isPresent()) {
            PromocionDetalle promocionDetalle = promocionDetalleOpt.get();
            promocionDetalleRepository.delete(promocionDetalle);
            return promocionDetalle;
        }
        return null;
    }
}
