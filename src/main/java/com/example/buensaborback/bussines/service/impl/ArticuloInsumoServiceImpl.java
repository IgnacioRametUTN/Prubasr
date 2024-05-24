package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.IArticuloInsumoService;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.domain.entities.Categoria;
import com.example.buensaborback.domain.entities.PromocionDetalle;
import com.example.buensaborback.domain.entities.UnidadMedida;
import com.example.buensaborback.repositories.ArticuloInsumoRepository;
import com.example.buensaborback.repositories.PromocionDetalleRepository;
import com.example.buensaborback.repositories.PromocionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ArticuloInsumoServiceImpl extends BaseServiceImpl<ArticuloInsumo,Long> implements IArticuloInsumoService {
    @Autowired
    private UnidadMedidaServiceImpl unidadMedidaService;
    @Autowired
    private CategoriaServiceImpl categoriaService;

    @Autowired
    private PromocionDetalleRepository promocionService;

    @Autowired
    private final ArticuloInsumoRepository articuloInsumoRepository;

    public ArticuloInsumoServiceImpl(ArticuloInsumoRepository articuloInsumoRepository) {
        this.articuloInsumoRepository = articuloInsumoRepository;
    }

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

    @Override
    public List<ArticuloInsumo> getAll(Optional<Long> categoriaOpt, Optional<Long> unidadMedidaOpt) {
        Categoria categoria;
        UnidadMedida unidadMedida;
        if(categoriaOpt.isPresent() && unidadMedidaOpt.isPresent()){
            categoria = categoriaService.getById(categoriaOpt.get());
            unidadMedida = unidadMedidaService.getById(unidadMedidaOpt.get());
          return  this.articuloInsumoRepository.findByCategoriaAndUnidadMedida(categoria, unidadMedida);
        }
        if(categoriaOpt.isPresent()){
            categoria = categoriaService.getById(categoriaOpt.get());
            return  this.articuloInsumoRepository.findByCategoria(categoria);
        }
        if(unidadMedidaOpt.isPresent()){
            unidadMedida = unidadMedidaService.getById(unidadMedidaOpt.get());
            return  this.articuloInsumoRepository.findByUnidadMedida(unidadMedida);
        }

        return super.getAll();
    }
}
