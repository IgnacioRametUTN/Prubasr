package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.IDomicilioService;
import com.example.buensaborback.domain.entities.Domicilio;
import com.example.buensaborback.domain.entities.Localidad;
import com.example.buensaborback.domain.entities.Pais;
import com.example.buensaborback.domain.entities.Provincia;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import com.example.buensaborback.repositories.DomicilioRepository;
import com.example.buensaborback.repositories.LocalidadRepository;
import com.example.buensaborback.repositories.PaisRepository;
import com.example.buensaborback.repositories.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomicilioServiceImpl implements IDomicilioService {
    private final DomicilioRepository domicilioRepository;
    private final LocalidadRepository localidadRepository;
    private final ProvinciaRepository provinciaRepository;
    private final PaisRepository paisRepository;

    public DomicilioServiceImpl(DomicilioRepository domicilioRepository, LocalidadRepository localidadRepository, ProvinciaRepository provinciaRepository, PaisRepository paisRepository) {
        this.domicilioRepository = domicilioRepository;
        this.localidadRepository = localidadRepository;
        this.provinciaRepository = provinciaRepository;
        this.paisRepository = paisRepository;
    }

    @Autowired


    @Override
    public Domicilio getDomicilioById(Long id){
        return this.domicilioRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Domicilio con ID %d no encontrado", id)));
    }
    @Override
    public boolean existsDomicilioById(Long id){
        return this.domicilioRepository.existsById(id);
    }

    @Override
    public List<Domicilio> findAll() {
        return this.domicilioRepository.findAll();
    }

    @Override
    public List<Localidad> findAllLocalidad() {
        return this.localidadRepository.findAll();
    }

    @Override
    public List<Provincia> findAllProvincia() {
        return this.provinciaRepository.findAll();
    }

    @Override
    public List<Domicilio> findAllAlta() {
        return this.domicilioRepository.findByAltaTrue();
    }

    @Override
    public List<Pais> findAllPais() {
        return this.paisRepository.findAll();
    }

    @Override
    public Domicilio update(Long id, Domicilio body) {
        this.existsDomicilioById(id);
        return this.domicilioRepository.save(body);
    }
    @Override
    public Domicilio create(Domicilio body) {
        return this.domicilioRepository.save(body);
    }
    @Override
    public Domicilio delete(Long id) {
        Domicilio domicilio = this.getDomicilioById(id);
        domicilio.setAlta(!domicilio.isAlta());
        return domicilio;
    }
}
//se te murio e