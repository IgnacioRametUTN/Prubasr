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
        //Verificar Stock de cada ArticuloInsumo
//        List<ArticuloManufacturadoDetalle> detallesValidos =  entity.getArticuloManufacturadoDetalles().stream().peek(System.out::println).filter(
//                detalle -> detalle.getCantidad() <= articuloInsumoService.getById(detalle.getArticuloInsumo().getId()).getStockActual()
//        ).toList();
        entity.setCategoria(categoriaService.getById(entity.getCategoria().getId()));
        entity.setUnidadMedida(unidadMedidaService.getById(entity.getUnidadMedida().getId()));
        Set<ArticuloManufacturadoDetalle> detallesValidos = entity.getArticuloManufacturadoDetalles();
        Set<ArticuloInsumo> nuevos = new HashSet<>();
        for (ArticuloManufacturadoDetalle detalle : detallesValidos){
            ArticuloInsumo articuloInsumo = articuloInsumoService.getById(detalle.getArticuloInsumo().getId());
            nuevos.add(articuloInsumo);
            detalle.setArticuloInsumo(articuloInsumo);
        }
        if(detallesValidos.size() != entity.getArticuloManufacturadoDetalles().size()) throw new InsufficientStockException("No hay insumos suficiente para preparar" + entity.getDenominacion());

        return super.create(entity);
    }
}
