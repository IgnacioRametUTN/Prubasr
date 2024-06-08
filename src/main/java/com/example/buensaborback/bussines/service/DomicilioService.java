package com.example.buensaborback.bussines.service;

import com.example.buensaborback.domain.entities.Domicilio;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import com.example.buensaborback.repositories.DomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DomicilioService {
    private final DomicilioRepository domicilioRepository;
    @Autowired
    public DomicilioService(DomicilioRepository domicilioRepository) {
        this.domicilioRepository = domicilioRepository;
    }
    public Domicilio getDomicilioById(Long id){
        return this.domicilioRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Domicilio con ID %d no encontrado", id)));
    }

    public boolean existsDomicilioById(Long id){
        return this.domicilioRepository.existsById(id);
    }
}
