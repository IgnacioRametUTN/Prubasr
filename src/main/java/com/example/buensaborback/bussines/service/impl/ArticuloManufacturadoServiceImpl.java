package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.IArticuloManufacturadoService;
import com.example.buensaborback.domain.entities.*;
import com.example.buensaborback.presentation.advice.exception.InsufficientStockException;
import com.example.buensaborback.repositories.ArticuloManufacturadoDetalleRepository;
import com.example.buensaborback.repositories.ArticuloManufacturadoRepository;
import com.example.buensaborback.repositories.CategoriaRepository;
import com.example.buensaborback.repositories.UnidadMedidaRepository;
import jakarta.transaction.Transactional;
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
    @Autowired
    private ArticuloManufacturadoRepository articuloManufacturadoRepository;
    @Autowired
    private ArticuloManufacturadoDetalleRepository articuloManufacturadoDetalleRepository;

    @Override
    @Transactional
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

    @Override
    @Transactional
    public ArticuloManufacturado update(ArticuloManufacturado entity) {
        Set<ArticuloManufacturadoDetalle> detallesViejo = new HashSet<>();
        detallesViejo=this.articuloManufacturadoRepository.getById(entity.getId()).getArticuloManufacturadoDetalles();
        entity.setCategoria(categoriaService.getById(entity.getCategoria().getId())); //Si id no se encuentra throws Exception en Repository
        entity.setUnidadMedida(unidadMedidaService.getById(entity.getUnidadMedida().getId()));//Si id no se encuentra throws Exception en Repository
        Set<ArticuloManufacturadoDetalle> detalles = new HashSet<>();

        for (ArticuloManufacturadoDetalle detalle : entity.getArticuloManufacturadoDetalles()){
            detalle.setArticuloManufacturado(entity);//Agrega Bidireccion
            detalle.setArticuloInsumo(articuloInsumoService.getById(detalle.getArticuloInsumo().getId()));//Si id no se encuentra throws Exception en Repository
            detalles.add(detalle);

        }
        System.out.println("Detalle Viejo: "+detallesViejo.toString());
        System.out.println("------------------------------");
        System.out.println("detalle editado: "+detalles.toString());
        for (ArticuloManufacturadoDetalle detalle  : detallesViejo) {
            if (!detalles.contains(detalle)) {
                System.out.println("Entre :D"+detalle.toString());
                detalle.setAlta(false);
                articuloManufacturadoDetalleRepository.save(detalle);
            }
        }

        entity.setArticuloManufacturadoDetalles(detalles);//Se guardan detalles con ArticuloInsumo de la DB
        return super.update(entity);
    }
}
