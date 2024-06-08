package com.example.buensaborback.bussines.service;

import com.example.buensaborback.domain.entities.Empresa;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import com.example.buensaborback.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {
    private final EmpresaRepository empresaRepository;
    @Autowired
    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public Empresa getEmpresaById(Long id){
        return this.empresaRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Empresa con ID %d no encontrado", id)));
    }

    public boolean existsEmpresaById(Long id){
        return this.empresaRepository.existsById(id);
    }
}

