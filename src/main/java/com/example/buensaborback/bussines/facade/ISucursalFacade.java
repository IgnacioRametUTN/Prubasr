package com.example.buensaborback.bussines.facade;

import com.example.buensaborback.domain.dtos.sucursal.SucursalDto;

import java.util.List;

public interface ISucursalFacade /*extends IBaseFacade<SucursalDto, Long>*/ {
    List<SucursalDto> findSucursalesByEmpresaId(Long id);
}
