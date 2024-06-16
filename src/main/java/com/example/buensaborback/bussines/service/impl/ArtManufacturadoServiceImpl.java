package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.IArtManufacturadoService;
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

import static java.util.Objects.nonNull;

@Service
public class ArtManufacturadoServiceImpl implements IArtManufacturadoService {
    private final ArticuloManufacturadoRepository articuloManufacturadoRepository;
    private final ArticuloInsumoServiceImpl articuloInsumoServiceImpl;
    private final CategoriaServiceImpl categoriaServiceImpl;
    private final UnidadMedidaServiceImpl unidadMedidaService;
    private final ArtManufacturadoDetalleServiceImpl artManufacturadoDetalleServiceImpl;
    private final PromocionDetalleServiceImpl promocionDetalleServiceImpl;

    public ArtManufacturadoServiceImpl(ArticuloManufacturadoRepository articuloManufacturadoRepository, ArticuloInsumoServiceImpl articuloInsumoServiceImpl, CategoriaServiceImpl categoriaServiceImpl, UnidadMedidaServiceImpl unidadMedidaService, ArtManufacturadoDetalleServiceImpl artManufacturadoDetalleServiceImpl, PromocionDetalleServiceImpl promocionDetalleServiceImpl) {
        this.articuloManufacturadoRepository = articuloManufacturadoRepository;
        this.articuloInsumoServiceImpl = articuloInsumoServiceImpl;
        this.categoriaServiceImpl = categoriaServiceImpl;
        this.unidadMedidaService = unidadMedidaService;
        this.artManufacturadoDetalleServiceImpl = artManufacturadoDetalleServiceImpl;
        this.promocionDetalleServiceImpl = promocionDetalleServiceImpl;
    }

    public ArticuloManufacturado getArticuloManufacturadoById(Long id) {
        return this.articuloManufacturadoRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Articulo Manufacturado con ID %d no encontrado", id)));
    }

    public boolean existsArticuloManufacturadoById(Long id) {
        return this.articuloManufacturadoRepository.existsById(id);
    }

    @Transactional
    public ArticuloManufacturado create(ArticuloManufacturado entity) {
        entity.setCategoria(categoriaServiceImpl.getCategoriaById(entity.getCategoria().getId()));
        entity.setUnidadMedida(unidadMedidaService.getUnidadMedidaById(entity.getUnidadMedida().getId()));

        if (nonNull(entity.getPromocionDetalle())) {
            // Actualizar la lista de PromocionDetalle
            entity.setPromocionDetalle(entity.getPromocionDetalle().stream()
                    .map(detalle -> {
                        PromocionDetalle promocionDetalle = promocionDetalleServiceImpl.getPromocionDetalleById(detalle.getId());
                        promocionDetalle.setArticulo(entity);
                        return promocionDetalle;
                    })
                    .collect(Collectors.toSet()));
        }
        entity.getArticuloManufacturadoDetalles()
                .forEach(detalle -> {
                    detalle.setArticuloInsumo(this.articuloInsumoServiceImpl.getArticuloInsumoById(detalle.getArticuloInsumo().getId()));
                    detalle.setArticuloManufacturado(entity);
                });
        return this.articuloManufacturadoRepository.save(entity);
    }

    @Override
    @Transactional
    public ArticuloManufacturado update(Long id, ArticuloManufacturado entity) {
        // Obtén el artículo manufacturado existente de la base de datos
        ArticuloManufacturado existingEntity = getArticuloManufacturadoById(id);

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
                detalle.setArticuloInsumo(articuloInsumoServiceImpl.getArticuloInsumoById(detalle.getArticuloInsumo().getId())); // Si id no se encuentra, lanza una excepción en Repository
                detalles.add(detalle);
            }
        }

        // Maneja los detalles que ya no están presentes en la entidad actualizada
        for (ArticuloManufacturadoDetalle detalleViejo : detallesViejo) {
            if (!detalles.contains(detalleViejo)) {
                System.out.println("Desactivando: " + detalleViejo.toString());
                detalleViejo.setAlta(false);
                artManufacturadoDetalleServiceImpl.create(detalleViejo);
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

    @Override
    public List<ArticuloManufacturado> getAll() {
        return this.articuloManufacturadoRepository.findAll();
    }

    @Override
    public ArticuloManufacturado delete(Long id) {
        ArticuloManufacturado articuloManufacturado = this.getArticuloManufacturadoById(id);
        articuloManufacturado.setAlta(!articuloManufacturado.isAlta());
        return this.articuloManufacturadoRepository.save(articuloManufacturado);
    }
}
