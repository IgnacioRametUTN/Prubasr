package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.domain.entities.Categoria;
import com.example.buensaborback.domain.entities.PromocionDetalle;
import com.example.buensaborback.domain.entities.UnidadMedida;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import com.example.buensaborback.repositories.ArticuloInsumoRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ArticuloInsumoServiceImpl {
    private final ArticuloInsumoRepository articuloInsumoRepository;
    private final UnidadMedidaServiceImpl unidadMedidaService;
    private final CategoriaServiceImpl categoriaService;
    private final PromocionDetalleService promocionDetalleService;

    public ArticuloInsumoServiceImpl(ArticuloInsumoRepository articuloInsumoRepository, UnidadMedidaServiceImpl unidadMedidaService, CategoriaServiceImpl categoriaService, PromocionDetalleService promocionDetalleService) {
        this.articuloInsumoRepository = articuloInsumoRepository;
        this.unidadMedidaService = unidadMedidaService;
        this.categoriaService = categoriaService;
        this.promocionDetalleService = promocionDetalleService;
    }

    public ArticuloInsumo getArticuloInsumoById(Long id){
        return this.articuloInsumoRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Articulo Insumo con ID %d no encontrado", id)));
    }

    public boolean existsArticuloInsumoById(Long id){
        return this.articuloInsumoRepository.existsById(id);
    }

    public ArticuloInsumo create(ArticuloInsumo entity){
        return this.articuloInsumoRepository.save(entity);
    }

    public ArticuloInsumo update(ArticuloInsumo entity) {
        // Actualizar las referencias a UnidadMedida y Categoria
        entity.setUnidadMedida(unidadMedidaService.getUnidadMedidaById(entity.getUnidadMedida().getId()));
        entity.setCategoria(categoriaService.getCategoriaById(entity.getCategoria().getId()));

        // Actualizar la lista de PromocionDetalle
        entity.setPromocionDetalle(entity.getPromocionDetalle().stream()
                .map(detalle -> {
                    PromocionDetalle promocionDetalle = promocionDetalleService.getPromocionDetalleById(detalle.getId());
                    promocionDetalle.setArticulo(entity);
                    return promocionDetalle;
                })
                .collect(Collectors.toSet()));

        // Guardar y devolver la entidad actualizada
        return articuloInsumoRepository.save(entity);
    }

    public ArticuloInsumo delete(Long id){
        ArticuloInsumo entity = this.getArticuloInsumoById(id);
        entity.setAlta(!entity.isAlta());
        return this.articuloInsumoRepository.save(entity);
    }


    public List<ArticuloInsumo> getAll(Optional<Long> categoriaOpt, Optional<Long> unidadMedidaOpt, Optional<String> searchOpt) {
        Categoria categoria = categoriaOpt.map(categoriaService::getCategoriaById).orElse(null); //Basicamente funciona así: si el Optional está vacío el map() no hace nada y salta al orElse y devuelve null, caso contrario ejecuta el metodo del map
        UnidadMedida unidadMedida = unidadMedidaOpt.map(unidadMedidaService::getUnidadMedidaById).orElse(null);
        String search = searchOpt.orElse("");

        if (categoria != null && unidadMedida != null) {
            return articuloInsumoRepository.findByCategoriaAndUnidadMedidaAndDenominacionStartingWithIgnoreCase(categoria, unidadMedida, search);
        } else if (categoria != null) {
            return articuloInsumoRepository.findByCategoriaAndDenominacionStartingWithIgnoreCase(categoria, search);
        } else if (unidadMedida != null) {
            return articuloInsumoRepository.findByUnidadMedidaAndDenominacionStartingWithIgnoreCase(unidadMedida, search);
        } else if (!search.isEmpty()) {
            return articuloInsumoRepository.findByDenominacionStartingWithIgnoreCase(search);
        } else {
            return articuloInsumoRepository.findAll();
        }
    }

}
