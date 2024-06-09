package com.example.buensaborback.bussines.service;
import com.example.buensaborback.domain.entities.Empresa;
import java.util.List;

public interface IEmpresaService {
    Empresa saveEmpresa(Empresa empresa);
    Empresa getEmpresaById(Long id);
    boolean existsEmpresaById(Long id);
    List<Empresa> getAll();
    List<Empresa> getAllAlta();
    Empresa updateEmpresa(Long id, Empresa empresa);
    void deleteEmpresa(Long id);
}
