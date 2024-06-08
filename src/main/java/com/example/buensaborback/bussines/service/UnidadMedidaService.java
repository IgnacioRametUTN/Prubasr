package com.example.buensaborback.bussines.service;

import com.example.buensaborback.domain.entities.UnidadMedida;
import com.example.buensaborback.presentation.advice.exception.DuplicateEntryException;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import com.example.buensaborback.repositories.UnidadMedidaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UnidadMedidaService {
    private final UnidadMedidaRepository unidadMedidaRepository;

    public UnidadMedidaService(UnidadMedidaRepository unidadMedidaRepository) {
        this.unidadMedidaRepository = unidadMedidaRepository;
    }

    public UnidadMedida getUnidadMedidaById(Long id){
        return this.unidadMedidaRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Unidad Medida con ID %d no encontrado", id)));
    }

    public boolean existsUnidadMedidaById(Long id){
        return this.unidadMedidaRepository.existsById(id);
    }

    public boolean existsUnidadMedidaByDenominacion(String denominacion){
        return unidadMedidaRepository.existsByDenominacionIgnoreCase(denominacion);
    }

    public UnidadMedida update(Long id, UnidadMedida body) {
        UnidadMedida unidadMedida = this.getUnidadMedidaById(id);
        if(!unidadMedida.getDenominacion().equalsIgnoreCase(body.getDenominacion())){ //validacion para permitir que se pueda hacer  update KG -> kg
            if(existsUnidadMedidaByDenominacion(body.getDenominacion())) throw new DuplicateEntryException(String.format("Ya existe una Unidad Medida con el nombre %s", body.getDenominacion()));
        }
        return this.unidadMedidaRepository.save(body);
    }

    public UnidadMedida create(UnidadMedida body) {
        if(existsUnidadMedidaByDenominacion(body.getDenominacion())) throw new DuplicateEntryException(String.format("Ya existe una Unidad Medida con el nombre %s", body.getDenominacion()));
        return this.unidadMedidaRepository.save(body);
    }

    public UnidadMedida delete(Long id) {
        UnidadMedida unidadMedidaExistente = this.getUnidadMedidaById(id);
        unidadMedidaExistente.setAlta(!unidadMedidaExistente.isAlta());
        return this.unidadMedidaRepository.save(unidadMedidaExistente);
    }

    public List<UnidadMedida> findAll() {
        return this.unidadMedidaRepository.findAll();
    }

    public List<UnidadMedida> findAllAlta() {
        return unidadMedidaRepository.findByAltaTrue();
    }
}
