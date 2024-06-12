package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.IPromocionService;
import com.example.buensaborback.domain.entities.Promocion;
import com.example.buensaborback.repositories.PromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromocionServiceImpl implements IPromocionService {

    @Autowired
    private PromocionRepository promocionRepository;

    @Override
    public Promocion getPromocionById(Long id) {
        return promocionRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existsPromocionById(Long id) {
        return promocionRepository.existsById(id);
    }

    @Override
    public Promocion create(Promocion entity) {
        return promocionRepository.save(entity);
    }

    @Override
    public Promocion update(Long id, Promocion entity) {
        if (existsPromocionById(id)) {
            entity.setId(id);
            return promocionRepository.save(entity);
        }
        return null;
    }

    @Override
    public List<Promocion> getAll() {
        return promocionRepository.findAll();
    }

    @Override
    public Promocion delete(Long id) {
        Optional<Promocion> promocionOpt = promocionRepository.findById(id);
        if (promocionOpt.isPresent()) {
            Promocion promocion = promocionOpt.get();
            promocionRepository.delete(promocion);
            return promocion;
        }
        return null;
    }
}
