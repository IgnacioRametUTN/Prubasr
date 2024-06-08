package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.EmpresaService;
import com.example.buensaborback.domain.entities.Empresa;

import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import com.example.buensaborback.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Override
    public Empresa saveEmpresa(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    @Override
    public Empresa getEmpresaById(Long id){
        return this.empresaRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Empresa con ID %d no encontrado", id)));
    }
    @Override
    public boolean existsEmpresaById(Long id){
        return this.empresaRepository.existsById(id);
    }

    @Override
    public List<Empresa> getAll() {
        return empresaRepository.findAll();
    }

    @Override
    public List<Empresa> getAllAlta() {
        return empresaRepository.findByAltaTrue();
    }

    @Override
    public Empresa updateEmpresa(Long id, Empresa empresa) {
        Optional<Empresa> existingEmpresa = empresaRepository.findById(id);
        if (existingEmpresa.isPresent()) {
            Empresa updatedEmpresa = existingEmpresa.get();
            updatedEmpresa.setNombre(empresa.getNombre());
            updatedEmpresa.setRazonSocial(empresa.getRazonSocial());
            updatedEmpresa.setCuil(empresa.getCuil());
            updatedEmpresa.setSucursales(empresa.getSucursales());
            updatedEmpresa.setImagen(empresa.getImagen());
            return empresaRepository.save(updatedEmpresa);
        } else {
            return null;
        }
    }

    @Override
    public void deleteEmpresa(Long id) {
        Empresa empresa = this.getEmpresaById(id);
        empresa.setAlta(!empresa.isAlta());
        empresaRepository.save(empresa);
    }
}
