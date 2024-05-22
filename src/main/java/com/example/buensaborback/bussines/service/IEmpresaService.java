package com.example.buensaborback.bussines.service;

import com.example.buensaborback.domain.entities.Empresa;

import java.util.List;

public interface IEmpresaService {
    List<Empresa> findAll();
    Empresa save(Empresa empresa);
}
