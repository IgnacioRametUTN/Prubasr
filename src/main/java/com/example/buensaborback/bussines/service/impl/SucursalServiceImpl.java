package com.example.buensaborback.bussines.service.impl;
import com.example.buensaborback.domain.entities.Empresa;
import com.example.buensaborback.domain.entities.Sucursal;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import com.example.buensaborback.repositories.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalServiceImpl{

    @Autowired
    private SucursalRepository sucursalRepository;
    @Autowired
    private  EmpresaServiceImpl empresaServiceImpl;

    public List<Sucursal> findAll() {
        return sucursalRepository.findAll();
    }

    public Sucursal save(Sucursal sucursal) throws NotFoundException {
        Empresa empresa = empresaServiceImpl.getById(sucursal.getEmpresa().getId());
        sucursal.setEmpresa(empresa);
        return super.create(sucursal);
    }

    @Override
    public List<Sucursal> findSucursalesByEmpresaId(Long id) {
        Empresa empresa = this.empresaServiceImpl.getById(id);
        return this.sucursalRepository.findSucursalByEmpresa(empresa);
    }


}
