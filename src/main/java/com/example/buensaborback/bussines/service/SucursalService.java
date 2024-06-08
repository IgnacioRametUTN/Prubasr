package com.example.buensaborback.bussines.service;
import com.example.buensaborback.domain.entities.Empresa;
import com.example.buensaborback.domain.entities.Sucursal;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import com.example.buensaborback.repositories.SucursalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalService {
    private final SucursalRepository sucursalRepository;
    private final EmpresaService empresaService;

    public SucursalService(SucursalRepository sucursalRepository, EmpresaService empresaService) {
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

    public List<Sucursal> findSucursalesByEmpresaId(Long id) {
        Empresa empresa = this.empresaService.getEmpresaById(id);
        return this.sucursalRepository.findSucursalByEmpresa(empresa);
    }


}
