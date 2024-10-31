package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.IPaisService;
import com.example.buensaborback.domain.entities.Pais;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import com.example.buensaborback.repositories.PaisRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaisServiceImpl implements IPaisService {

    private final PaisRepository paisRepository;

    public PaisServiceImpl(PaisRepository paisRepository){
        this.paisRepository = paisRepository;
    }
    @Override
    public List<Pais> findAllPais() {
        return this.paisRepository.findAll();
    }

    @Override
    public Pais getPaisById(Long idPais){
        return this.paisRepository.findById(idPais).orElseThrow(() -> new NotFoundException(String.format("Pais con ID %d no encontrado", idPais)));
    }

    @Override
    public boolean existPaisById(Long idPais){
        return this.paisRepository.existsById(idPais);
    }
}
