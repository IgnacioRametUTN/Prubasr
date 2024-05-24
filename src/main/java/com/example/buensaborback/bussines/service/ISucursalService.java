package com.example.buensaborback.bussines.service;
import com.example.buensaborback.domain.entities.Empresa;
import com.example.buensaborback.domain.entities.Sucursal;

import java.util.List;

public interface ISucursalService extends IBaseService<Sucursal, Long> {
    List<Sucursal> findSucursalesByEmpresaId(Long id);
}
