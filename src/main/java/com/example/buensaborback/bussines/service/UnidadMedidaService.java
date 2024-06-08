package com.example.buensaborback.bussines.service;

import com.example.buensaborback.domain.entities.UnidadMedida;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import com.example.buensaborback.repositories.UnidadMedidaRepository;
import org.springframework.stereotype.Service;

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
}
