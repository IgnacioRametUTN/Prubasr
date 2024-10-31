package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.IPaisService;
import com.example.buensaborback.bussines.service.IProvinciaService;
import com.example.buensaborback.domain.entities.Provincia;
import com.example.buensaborback.domain.entities.Pais;
import com.example.buensaborback.domain.entities.Provincia;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import com.example.buensaborback.repositories.ProvinciaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvinciaServiceImpl implements IProvinciaService {

    private final ProvinciaRepository provinciaRepository;

    private final IPaisService paisService;

    public ProvinciaServiceImpl(ProvinciaRepository provinciaRepository, PaisServiceImpl paisService){
        this.provinciaRepository = provinciaRepository;
        this.paisService = paisService;
    }
    @Override
    public List<Provincia> findProvinciasByPais(Long idPais){
        Pais pais =  this.paisService.getPaisById(idPais);
        return this.provinciaRepository.findByPais(pais);
    };

    @Override
    public Provincia getProvinciaById(Long idPais){
        return this.provinciaRepository.findById(idPais).orElseThrow(() -> new NotFoundException(String.format("Provincia con ID %d no encontrado", idPais)));
    }
    public boolean existProvinciaById(Long idProvincia){
        return this.provinciaRepository.existsById(idProvincia);
    }

}
