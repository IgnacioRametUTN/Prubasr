package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.domain.entities.Domicilio;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import com.example.buensaborback.repositories.DomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DomicilioServiceImpl {
    private final DomicilioRepository domicilioRepository;
    @Autowired
    public DomicilioServiceImpl(DomicilioRepository domicilioRepository) {
        this.domicilioRepository = domicilioRepository;
    }
    public Domicilio getDomicilioById(Long id){
        return this.domicilioRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Domicilio con ID %d no encontrado", id)));
    }

    public boolean existsDomicilioById(Long id){
        return this.domicilioRepository.existsById(id);
    }
}
