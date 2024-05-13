package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.IArticuloManufacturadoService;
import com.example.buensaborback.domain.entities.*;
import com.example.buensaborback.presentation.advice.exception.InsufficientStockException;
import com.example.buensaborback.repositories.CategoriaRepository;
import com.example.buensaborback.repositories.UnidadMedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ArticuloManufacturadoServiceImpl extends BaseServiceImpl<ArticuloManufacturado,Long> implements IArticuloManufacturadoService {

    @Autowired
    private ArticuloInsumoServiceImpl articuloInsumoService;
    @Autowired
    private CategoriaServiceImpl categoriaService;
    @Autowired
    private UnidadMedidaRepository unidadMedidaService;

    @Override
    public ArticuloManufacturado create(ArticuloManufacturado entity) {
        entity.setCategoria(categoriaService.getById(entity.getCategoria().getId())); //Si id no se encuentra throws Exception en Repository
        entity.setUnidadMedida(unidadMedidaService.getById(entity.getUnidadMedida().getId()));//Si id no se encuentra throws Exception en Repository

        Set<ArticuloManufacturadoDetalle> detalles = new HashSet<>();
        for (ArticuloManufacturadoDetalle detalle : entity.getArticuloManufacturadoDetalles()){
            detalle.setArticuloManufacturado(entity);//Agrega Bidireccion
            detalle.setArticuloInsumo(articuloInsumoService.getById(detalle.getArticuloInsumo().getId()));//Si id no se encuentra throws Exception en Repository
            detalles.add(detalle);
        }

        entity.setArticuloManufacturadoDetalles(detalles);//Se guardan detalles con ArticuloInsumo de la DB
        return super.create(entity);
    }
}
