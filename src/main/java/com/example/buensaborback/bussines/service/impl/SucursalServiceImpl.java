package com.example.buensaborback.bussines.service.impl;
import com.example.buensaborback.bussines.service.ISucursalService;
import com.example.buensaborback.domain.entities.Empresa;
import com.example.buensaborback.domain.entities.Sucursal;
import com.example.buensaborback.repositories.EmpresaRepository;
import com.example.buensaborback.repositories.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalServiceImpl /*extends BaseServiceImpl<Sucursal,Long> implements ISucursalService*/ {
    @Autowired
    private SucursalRepository sucursalRepository;

    public List<Sucursal> findAll() {
        return sucursalRepository.findAll();
    }

    public Sucursal save(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }

}
