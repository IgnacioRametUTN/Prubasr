package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.domain.entities.Empresa;
import com.example.buensaborback.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaServiceImpl {


        @Autowired
        private EmpresaRepository empresaRepository;

        public List<Empresa> findAll() {
            return empresaRepository.findAll();
        }

        public Empresa save(Empresa empresa) {
            return empresaRepository.save(empresa);
        }

        // Otros métodos CRUD
    }

