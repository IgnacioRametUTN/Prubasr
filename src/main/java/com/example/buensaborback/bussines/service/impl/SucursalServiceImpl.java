package com.example.buensaborback.bussines.service.impl;
import com.example.buensaborback.domain.entities.Empresa;
import com.example.buensaborback.domain.entities.Sucursal;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import com.example.buensaborback.repositories.SucursalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalServiceImpl{
    private final SucursalRepository sucursalRepository;
    private final EmpresaServiceImpl empresaService;

    public SucursalServiceImpl(SucursalRepository sucursalRepository, EmpresaServiceImpl empresaService) {
        this.sucursalRepository = sucursalRepository;
        this.empresaService = empresaService;
    }

    public Sucursal getSucursalById(Long id){
        return this.sucursalRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Sucursal con ID %d no encontrado", id)));
    }

    public boolean existsSucursalById(Long id){
        return this.sucursalRepository.existsById(id);
    }
    public List<Sucursal> findAll() {
        return sucursalRepository.findAll();
    }

    public Sucursal save(Sucursal sucursal){
        Empresa empresa = empresaService.getEmpresaById(sucursal.getEmpresa().getId());
        sucursal.setEmpresa(empresa);
        return this.sucursalRepository.save(sucursal);
    }

    @Override
    public List<Sucursal> findSucursalesByEmpresaId(Long id) {
        Empresa empresa = this.empresaService.getById(id);
        return this.sucursalRepository.findSucursalByEmpresa(empresa);
    }


}
