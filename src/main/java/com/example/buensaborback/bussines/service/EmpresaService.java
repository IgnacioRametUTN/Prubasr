package com.example.buensaborback.bussines.service;
import com.example.buensaborback.domain.entities.Empresa;
import java.util.List;

public interface EmpresaService {
    Empresa saveEmpresa(Empresa empresa);
    Empresa getEmpresaById(Long id);
    List<Empresa> getAllEmpresas();
    Empresa updateEmpresa(Long id, Empresa empresa);
    void deleteEmpresa(Long id);
}
