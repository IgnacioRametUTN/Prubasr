package com.example.buensaborback.bussines.service;

import com.example.buensaborback.domain.entities.Sucursal;
import java.util.List;

public interface ISucursalService {
    Sucursal saveSucursal(Sucursal sucursal);
    Sucursal getSucursalById(Long id);
    List<Sucursal> getAllSucursales();
    Sucursal updateSucursal(Long id, Sucursal sucursal);
    void deleteSucursal(Long id);
    List<Sucursal> getSucursalesByEmpresaId(Long empresaId);
}
