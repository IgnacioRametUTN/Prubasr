package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.IPromocionService;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.domain.entities.Pedido;
import com.example.buensaborback.domain.entities.Promocion;
import com.example.buensaborback.domain.entities.PromocionDetalle;
import com.example.buensaborback.repositories.PromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PromocionServiceImpl implements IPromocionService {


    @Autowired
    private PromocionRepository promocionRepository;
    private final ArtManufacturadoServiceImpl artManufacturadoService;

    public PromocionServiceImpl(ArtManufacturadoServiceImpl artManufacturadoService) {
        this.artManufacturadoService = artManufacturadoService;
    }

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
        System.out.println(entity.toString());
        for (PromocionDetalle detalle: entity.getDetallesPromocion()) {
            ArticuloManufacturado articulo = artManufacturadoService.getArticuloManufacturadoById(detalle.getArticulo().getId());

            detalle.setArticulo(articulo);
            detalle.setPromocion(entity);
        }
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
