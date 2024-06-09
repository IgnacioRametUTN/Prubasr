package com.example.buensaborback.bussines.service;

import com.example.buensaborback.bussines.service.impl.CategoriaServiceImpl;
import com.example.buensaborback.bussines.service.impl.UnidadMedidaServiceImpl;
import com.example.buensaborback.domain.entities.*;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import com.example.buensaborback.repositories.ArticuloManufacturadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ArticuloManufacturadoService {
    private final ArticuloManufacturadoRepository articuloManufacturadoRepository;
    private final ArticuloInsumoService articuloInsumoService;
    private final CategoriaServiceImpl categoriaServiceImpl;
    private final UnidadMedidaServiceImpl unidadMedidaService;
    private final ArticuloManufacturadoDetalleService articuloManufacturadoDetalleService;
    private final PromocionDetalleService promocionDetalleService;

    public ArticuloManufacturadoService(ArticuloManufacturadoRepository articuloManufacturadoRepository, ArticuloInsumoService articuloInsumoService, CategoriaServiceImpl categoriaServiceImpl, UnidadMedidaServiceImpl unidadMedidaService, ArticuloManufacturadoDetalleService articuloManufacturadoDetalleService, PromocionDetalleService promocionDetalleService) {
        this.articuloManufacturadoRepository = articuloManufacturadoRepository;
        this.articuloInsumoService = articuloInsumoService;
        this.categoriaServiceImpl = categoriaServiceImpl;
        this.unidadMedidaService = unidadMedidaService;
        this.articuloManufacturadoDetalleService = articuloManufacturadoDetalleService;
        this.promocionDetalleService = promocionDetalleService;
    }

    public ArticuloManufacturado getArticuloManufacturadoById(Long id){
        return this.articuloManufacturadoRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Articulo Manufacturado con ID %d no encontrado", id)));
    }

    public boolean existsArticuloManufacturadoById(Long id){
        return this.articuloManufacturadoRepository.existsById(id);
    }
    @Transactional
    public ArticuloManufacturado create(ArticuloManufacturado entity) {
        entity.setCategoria(categoriaServiceImpl.getCategoriaById(entity.getCategoria().getId()));
        entity.setUnidadMedida(unidadMedidaService.getUnidadMedidaById(entity.getUnidadMedida().getId()));

        // Actualizar la lista de PromocionDetalle
        entity.setPromocionDetalle(entity.getPromocionDetalle().stream()
                .map(detalle -> {
                    PromocionDetalle promocionDetalle = promocionDetalleService.getPromocionDetalleById(detalle.getId());
                    promocionDetalle.setArticulo(entity);
                    return promocionDetalle;
                })
                .collect(Collectors.toSet()));

        // Actualizar la lista de Articulo Manufacturado Detalle
        entity.setArticuloManufacturadoDetalles(entity.getArticuloManufacturadoDetalles().stream()
                .map(detalle -> {
                    ArticuloManufacturadoDetalle articuloManufacturadoDetalle = articuloManufacturadoDetalleService.getArticuloManufacturadoDetalleById(detalle.getId());
                    articuloManufacturadoDetalle.setArticuloManufacturado(entity);
                    return articuloManufacturadoDetalle;
                })
                .collect(Collectors.toSet()));
        Set<ArticuloManufacturadoDetalle> detalles = new HashSet<>();

        return this.articuloManufacturadoRepository.save(entity);
    }

    @Transactional
    public ArticuloManufacturado update(ArticuloManufacturado entity) {
        // Obtén el artículo manufacturado existente de la base de datos
        ArticuloManufacturado existingEntity = articuloManufacturadoRepository.getById(entity.getId());

        // Obtener los detalles antiguos
        Set<ArticuloManufacturadoDetalle> detallesViejo = existingEntity.getArticuloManufacturadoDetalles();

        // Asignar las entidades relacionadas
        entity.setCategoria(categoriaServiceImpl.getCategoriaById(entity.getCategoria().getId())); // Si id no se encuentra, lanza una excepción en Repository
        entity.setUnidadMedida(unidadMedidaService.getUnidadMedidaById(entity.getUnidadMedida().getId())); // Si id no se encuentra, lanza una excepción en Repository

        // Prepara un nuevo conjunto para los detalles
        Set<ArticuloManufacturadoDetalle> detalles = new HashSet<>();

        for (ArticuloManufacturadoDetalle detalle : entity.getArticuloManufacturadoDetalles()) {
            // Verifica si el detalle ya existe en los detalles viejos por ArticuloInsumo ID
            ArticuloManufacturadoDetalle existingDetalle = detallesViejo.stream()
                    .filter(d -> d.getArticuloInsumo().getId().equals(detalle.getArticuloInsumo().getId()))
                    .findFirst()
                    .orElse(null);

            if (existingDetalle != null) {
                // Actualiza el detalle existente
                existingDetalle.setCantidad(detalle.getCantidad()); // Actualiza otras propiedades según sea necesario
                existingDetalle.setAlta(true); // Marca como activo
                detalles.add(existingDetalle);
            } else {
                // Si el detalle no existe, crea uno nuevo
                detalle.setArticuloManufacturado(entity); // Mantén la relación bidireccional
                detalle.setArticuloInsumo(articuloInsumoService.getArticuloInsumoById(detalle.getArticuloInsumo().getId())); // Si id no se encuentra, lanza una excepción en Repository
                detalles.add(detalle);
            }
        }

        // Maneja los detalles que ya no están presentes en la entidad actualizada
        for (ArticuloManufacturadoDetalle detalleViejo : detallesViejo) {
            if (!detalles.contains(detalleViejo)) {
                System.out.println("Desactivando: " + detalleViejo.toString());
                detalleViejo.setAlta(false);
                articuloManufacturadoDetalleService.create(detalleViejo);
            }
        }

        // Asigna el nuevo conjunto de detalles a la entidad principal
        entity.setArticuloManufacturadoDetalles(detalles);

        // Llama al método update de la clase base
        return this.articuloManufacturadoRepository.save(entity);
    }

    public List<ArticuloManufacturado> getAll(Optional<Long> categoriaOpt, Optional<Long> unidadMedidaOpt, Optional<String> searchOpt) {
        Categoria categoria = categoriaOpt.map(categoriaServiceImpl::getCategoriaById).orElse(null); //Basicamente funciona así: si el Optional está vacío el map() no hace nada y salta al orElse y devuelve null, caso contrario ejecuta el metodo del map
        UnidadMedida unidadMedida = unidadMedidaOpt.map(unidadMedidaService::getUnidadMedidaById).orElse(null);
        String search = searchOpt.orElse("");

        if (categoria != null && unidadMedida != null) {
            return articuloManufacturadoRepository.findByCategoriaAndUnidadMedidaAndDenominacionStartingWithIgnoreCase(categoria, unidadMedida, search);
        } else if (categoria != null) {
            return articuloManufacturadoRepository.findByCategoriaAndDenominacionStartingWithIgnoreCase(categoria, search);
        } else if (unidadMedida != null) {
            return articuloManufacturadoRepository.findByUnidadMedidaAndDenominacionStartingWithIgnoreCase(unidadMedida, search);
        } else if (!search.isEmpty()) {
            return articuloManufacturadoRepository.findByDenominacionStartingWithIgnoreCase(search);
        } else {
            return articuloManufacturadoRepository.findAll();
        }
    }
}
