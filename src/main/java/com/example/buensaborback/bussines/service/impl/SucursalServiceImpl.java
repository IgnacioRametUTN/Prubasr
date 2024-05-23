package com.example.buensaborback.bussines.service.impl;
import com.example.buensaborback.bussines.service.ISucursalService;
import com.example.buensaborback.domain.entities.Empresa;
import com.example.buensaborback.domain.entities.Sucursal;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import com.example.buensaborback.repositories.EmpresaRepository;
import com.example.buensaborback.repositories.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SucursalServiceImpl /*extends BaseServiceImpl<Sucursal,Long> implements ISucursalService*/ {
    @Autowired
    private SucursalRepository sucursalRepository;
    @Autowired
    private  EmpresaRepository empresaRepository;

    public List<Sucursal> findAll() {
        return sucursalRepository.findAll();
    }

    public Sucursal save(Sucursal sucursal) throws NotFoundException {
        // Buscar la empresa asociada a la sucursal
        Empresa empresa = empresaRepository.findById(sucursal.getEmpresa().getId())
                .orElseThrow(() -> new NotFoundException("No se encontró la empresa asociada a la sucursal"));

        // Asignar la empresa a la sucursal
        sucursal.setEmpresa(empresa);

        // Guardar la sucursal
        return sucursalRepository.save(sucursal);
    }

    public Sucursal findById(Long id) {
        Optional<Sucursal> optionalSucursal = sucursalRepository.findById(id);
        return optionalSucursal.orElse(null);
    }


    public List<Sucursal> findByEmpresaId(Long empresaId) {
        // Utiliza el método findByEmpresaId de tu repositorio para buscar las sucursales por ID de empresa
        return sucursalRepository.findByEmpresaId(empresaId);
    }
}
