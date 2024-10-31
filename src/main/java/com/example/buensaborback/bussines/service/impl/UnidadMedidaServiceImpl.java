package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.IUnidadMedidaService;
import com.example.buensaborback.domain.entities.UnidadMedida;
import com.example.buensaborback.presentation.advice.exception.DuplicateEntryException;
import com.example.buensaborback.presentation.advice.exception.EntityInUseException;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import com.example.buensaborback.repositories.UnidadMedidaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadMedidaServiceImpl implements IUnidadMedidaService {
    private final UnidadMedidaRepository unidadMedidaRepository;

    public UnidadMedidaServiceImpl(UnidadMedidaRepository unidadMedidaRepository) {
        this.unidadMedidaRepository = unidadMedidaRepository;
    }
    @Override
    public UnidadMedida getUnidadMedidaById(Long id){
        return this.unidadMedidaRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Unidad Medida con ID %d no encontrado", id)));
    }
    @Override
    public boolean existsUnidadMedidaById(Long id){
        return this.unidadMedidaRepository.existsById(id);
    }

    @Override
    public boolean existsUnidadMedidaByDenominacion(String denominacion){
        return unidadMedidaRepository.existsByDenominacionIgnoreCase(denominacion);
    }
    @Override
    public UnidadMedida update(Long id, UnidadMedida body) {
        UnidadMedida unidadMedida = this.getUnidadMedidaById(id);
        if(!unidadMedida.getDenominacion().equalsIgnoreCase(body.getDenominacion())){ //validacion para permitir que se pueda hacer  update KG -> kg
            if(existsUnidadMedidaByDenominacion(body.getDenominacion())) throw new DuplicateEntryException(String.format("Ya existe una Unidad Medida con el nombre %s", body.getDenominacion()));
        }
        return this.unidadMedidaRepository.save(body);
    }
    @Override
//    @Transactional
    public UnidadMedida create(UnidadMedida body) {
        if(existsUnidadMedidaByDenominacion(body.getDenominacion())) throw new DuplicateEntryException(String.format("Ya existe una Unidad Medida con el nombre %s", body.getDenominacion()));
        return this.unidadMedidaRepository.save(body);
    }
    @Override
    public UnidadMedida delete(Long id) {
        if (unidadMedidaRepository.existsInArticuloInsumoByUnidadMedida(id)||unidadMedidaRepository.existsInArticuloManufacturadoByUnidadMedida(id)){
            throw new EntityInUseException("La unidad de medida tiene articulos relacionados");
        }
        UnidadMedida unidadMedidaExistente = this.getUnidadMedidaById(id);
        unidadMedidaExistente.setAlta(!unidadMedidaExistente.isAlta());
        return this.unidadMedidaRepository.save(unidadMedidaExistente);
    }
    @Override
    public List<UnidadMedida> findAll() {
        return this.unidadMedidaRepository.findAll();
    }

    @Override
    public List<UnidadMedida> findAllAlta() {
        return unidadMedidaRepository.findByAltaTrue();
    }
}
