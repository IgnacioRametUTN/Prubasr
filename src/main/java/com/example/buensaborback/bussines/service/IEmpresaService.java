package com.example.buensaborback.bussines.service;
import com.example.buensaborback.domain.entities.Empresa;
import com.example.buensaborback.domain.entities.Imagen;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface IEmpresaService {
    Empresa saveEmpresa(Empresa empresa);
    Empresa getEmpresaById(Long id);
    boolean existsEmpresaById(Long id);
    List<Empresa> getAll();
    List<Empresa> getAllAlta();
    Empresa updateEmpresa(Long id, Empresa empresa);
    void deleteEmpresa(Long id);
    Set<Imagen> uploadImages(MultipartFile[] files, Long id);
}
