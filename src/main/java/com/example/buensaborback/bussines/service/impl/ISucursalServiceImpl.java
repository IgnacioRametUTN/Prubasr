package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.ISucursalService;
import com.example.buensaborback.domain.entities.Sucursal;

import com.example.buensaborback.repositories.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ISucursalServiceImpl implements ISucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    @Override
    public Sucursal saveSucursal(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }

    @Override
    public Sucursal getSucursalById(Long id) {
        Optional<Sucursal> sucursal = sucursalRepository.findById(id);
        return sucursal.orElse(null);
    }

    @Override
    public List<Sucursal> getAllSucursales() {
        return sucursalRepository.findAll();
    }

    @Override
    public Sucursal updateSucursal(Long id, Sucursal sucursal) {
        Optional<Sucursal> existingSucursal = sucursalRepository.findById(id);
        if (existingSucursal.isPresent()) {
            Sucursal updatedSucursal = existingSucursal.get();
            updatedSucursal.setNombre(sucursal.getNombre());
            updatedSucursal.setHorarioApertura(sucursal.getHorarioApertura());
            updatedSucursal.setHorarioCierre(sucursal.getHorarioCierre());
            updatedSucursal.setDomicilio(sucursal.getDomicilio());
            updatedSucursal.setCategorias(sucursal.getCategorias());
            updatedSucursal.setPedidos(sucursal.getPedidos());
            updatedSucursal.setPromociones(sucursal.getPromociones());
            updatedSucursal.setEmpresa(sucursal.getEmpresa());
            return sucursalRepository.save(updatedSucursal);
        } else {
            return null;
        }
    }

    @Override
    public void deleteSucursal(Long id) {
        sucursalRepository.deleteById(id);
    }

    @Override
    public List<Sucursal> getSucursalesByEmpresaId(Long empresaId) {
        return sucursalRepository.findByEmpresaId(empresaId);
    }
}
