package com.example.buensaborback.bussines.service;

import com.example.buensaborback.domain.entities.ArticuloManufacturadoDetalle;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import com.example.buensaborback.repositories.ArticuloManufacturadoDetalleRepository;
import org.springframework.stereotype.Service;

@Service
public class ArticuloManufacturadoDetalleService {
    private final ArticuloManufacturadoDetalleRepository articuloManufacturadoDetalleRepository;

    public ArticuloManufacturadoDetalleService(ArticuloManufacturadoDetalleRepository articuloManufacturadoDetalleRepository) {
        this.articuloManufacturadoDetalleRepository = articuloManufacturadoDetalleRepository;
    }

    public ArticuloManufacturadoDetalle getArticuloManufacturadoDetalleById(Long id){
        return this.articuloManufacturadoDetalleRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Articulo Manufacturado Detalle con ID %d no encontrado", id)));
    }

    public boolean existsArticuloManufacturadoDetalleById(Long id){
        return this.articuloManufacturadoDetalleRepository.existsById(id);
    }

    public ArticuloManufacturadoDetalle create(ArticuloManufacturadoDetalle articuloManufacturadoDetalle) {
        return this.articuloManufacturadoDetalleRepository.save(articuloManufacturadoDetalle);
    }
}
