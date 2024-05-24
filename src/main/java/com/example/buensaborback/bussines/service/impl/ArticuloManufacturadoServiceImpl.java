package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.IArticuloManufacturadoService;
import com.example.buensaborback.domain.entities.*;
import com.example.buensaborback.repositories.ArticuloManufacturadoDetalleRepository;
import com.example.buensaborback.repositories.ArticuloManufacturadoRepository;
import com.example.buensaborback.repositories.UnidadMedidaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
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
        // Obtén el artículo manufacturado existente de la base de datos
        ArticuloManufacturado existingEntity = articuloManufacturadoRepository.getById(entity.getId());

        // Obtener los detalles antiguos
        Set<ArticuloManufacturadoDetalle> detallesViejo = existingEntity.getArticuloManufacturadoDetalles();

        // Asignar las entidades relacionadas
        entity.setCategoria(categoriaService.getById(entity.getCategoria().getId())); // Si id no se encuentra, lanza una excepción en Repository
        entity.setUnidadMedida(unidadMedidaService.getById(entity.getUnidadMedida().getId())); // Si id no se encuentra, lanza una excepción en Repository

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
                detalle.setArticuloInsumo(articuloInsumoService.getById(detalle.getArticuloInsumo().getId())); // Si id no se encuentra, lanza una excepción en Repository
                detalles.add(detalle);
            }
        }

        // Maneja los detalles que ya no están presentes en la entidad actualizada
        for (ArticuloManufacturadoDetalle detalleViejo : detallesViejo) {
            if (!detalles.contains(detalleViejo)) {
                System.out.println("Desactivando: " + detalleViejo.toString());
                detalleViejo.setAlta(false);
                articuloManufacturadoDetalleRepository.save(detalleViejo);
            }
        }

        // Asigna el nuevo conjunto de detalles a la entidad principal
        entity.setArticuloManufacturadoDetalles(detalles);

        // Llama al método update de la clase base
        return super.update(entity);
    }

    @Override
    public List<ArticuloManufacturado> getAll(Optional<Long> categoriaOpt, Optional<Long> unidadMedidaOpt, Optional<String> denominacionOpt) {
        Categoria categoria = null;
        UnidadMedida unidadMedida = null;
        String denominacion = "";

        if(categoriaOpt.isPresent()) {
            categoria = categoriaService.getById(categoriaOpt.get());
        }

        if(unidadMedidaOpt.isPresent()) {
            unidadMedida = unidadMedidaService.getById(unidadMedidaOpt.get());
        }

        if(denominacionOpt.isPresent()) {
            denominacion = denominacionOpt.get();
        }

        if(categoria != null && unidadMedida != null ){
            return this.articuloManufacturadoRepository.findByCategoriaAndUnidadMedidaAndDenominacionStartingWithIgnoreCase(categoria, unidadMedida, denominacion);
        }

        if(categoria != null){
            return this.articuloManufacturadoRepository.findByCategoriaAndDenominacionStartingWithIgnoreCase(categoria, denominacion);
        }

        if(unidadMedida != null){
            return this.articuloManufacturadoRepository.findByUnidadMedidaAndDenominacionStartingWithIgnoreCase(unidadMedida, denominacion);
        }

        if(denominacion != null){
            return this.articuloManufacturadoRepository.findByDenominacionStartingWithIgnoreCase(denominacion);
        }

        return super.getAll();
    }
}
