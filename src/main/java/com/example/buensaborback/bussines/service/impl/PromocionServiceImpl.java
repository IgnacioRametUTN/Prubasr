package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.IArtManufacturadoService;
import com.example.buensaborback.bussines.service.IArticuloInsumoService;
import com.example.buensaborback.bussines.service.IPromocionDetalleService;
import com.example.buensaborback.bussines.service.IPromocionService;
import com.example.buensaborback.domain.entities.*;
import com.example.buensaborback.repositories.PromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PromocionServiceImpl implements IPromocionService {


    private final PromocionRepository promocionRepository;
    private final IPromocionDetalleService promocionDetalleService;
    private final IArtManufacturadoService artManufacturadoService;
    private final IArticuloInsumoService articuloInsumoService;

    public PromocionServiceImpl(PromocionRepository promocionRepository, PromocionDetalleServiceImpl promocionDetalleService, ArtManufacturadoServiceImpl artManufacturadoService, ArticuloInsumoServiceImpl articuloInsumoService) {
        this.promocionRepository = promocionRepository;
        this.promocionDetalleService = promocionDetalleService;
        this.artManufacturadoService = artManufacturadoService;
        this.articuloInsumoService = articuloInsumoService;
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
            Articulo articulo = getArticulo(detalle);;
            detalle.setArticulo(articulo);
            detalle.setPromocion(entity);
        }
        return promocionRepository.save(entity);
    }

    private Articulo getArticulo(PromocionDetalle detalle) {
        Articulo articulo;
        if(this.artManufacturadoService.existsArticuloManufacturadoById(detalle.getArticulo().getId())){
           articulo = this.artManufacturadoService.getArticuloManufacturadoById(detalle.getArticulo().getId());
        }else{
            articulo = this.articuloInsumoService.getArticuloInsumoById(detalle.getArticulo().getId());
        }
        return articulo;
    }


    @Override
    public Promocion update(Long id, Promocion entity) {
        getPromocionById(id);
        Set<PromocionDetalle> promocionDetalles = new HashSet<>();
        for (PromocionDetalle promocionDetalle : entity.getDetallesPromocion()) {
            if(promocionDetalle.getId() != 0){
                PromocionDetalle promocionDetalleExistente = this.promocionDetalleService.getPromocionDetalleById(promocionDetalle.getId());
                promocionDetalleExistente.setCantidad(promocionDetalle.getCantidad());
                promocionDetalleExistente.setArticulo(getArticulo(promocionDetalle));
                promocionDetalles.add(promocionDetalleExistente);
            }else{
                promocionDetalle.setArticulo(getArticulo(promocionDetalle));
                promocionDetalle.setPromocion(entity);
                promocionDetalles.add(promocionDetalle);
            }

        }
        entity.setDetallesPromocion(promocionDetalles);
        return this.promocionRepository.save(entity);
    }

    @Override
    public List<Promocion> getAll() {
        return promocionRepository.findAll();
    }

    @Override
    public Promocion delete(Long id) {
        Promocion promocion = this.getPromocionById(id);
        promocion.setAlta(!promocion.isAlta());
        return this.promocionRepository.save(promocion);
    }
}
