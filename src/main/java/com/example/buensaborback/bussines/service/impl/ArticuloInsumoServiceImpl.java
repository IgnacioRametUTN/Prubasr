package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.IArticuloInsumoService;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.domain.entities.PromocionDetalle;
import com.example.buensaborback.repositories.PromocionDetalleRepository;
import com.example.buensaborback.repositories.PromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ArticuloInsumoServiceImpl extends BaseServiceImpl<ArticuloInsumo,Long> implements IArticuloInsumoService {
    @Autowired
    private UnidadMedidaServiceImpl unidadMedidaService;
    @Autowired
    private CategoriaServiceImpl categoriaService;
    @Autowired
    private PromocionDetalleRepository promocionService;
    @Override
    public ArticuloInsumo update(ArticuloInsumo entity) {
        entity.setUnidadMedida(unidadMedidaService.getById(entity.getUnidadMedida().getId()));
        entity.setCategoria(categoriaService.getById(entity.getCategoria().getId()));
        Set<PromocionDetalle> newDetalles = new HashSet<>();
        for (PromocionDetalle detalle : entity.getPromocionDetalle()){
            PromocionDetalle promocionDetalle = promocionService.getById(detalle.getId());
            promocionDetalle.setArticulo(entity);
            newDetalles.add(promocionDetalle);
        }

        entity.setPromocionDetalle(newDetalles);

        return super.update(entity);
    }
}
