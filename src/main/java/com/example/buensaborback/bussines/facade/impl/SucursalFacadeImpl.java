package com.example.buensaborback.bussines.facade.impl;

import com.example.buensaborback.bussines.facade.ISucursalFacade;
import com.example.buensaborback.bussines.mappers.ISucursalMapper;
import com.example.buensaborback.bussines.service.ISucursalService;
import com.example.buensaborback.bussines.service.impl.SucursalServiceImpl;
import com.example.buensaborback.domain.dtos.sucursal.SucursalDto;
import com.example.buensaborback.domain.entities.Sucursal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalFacadeImpl extends BaseFacadeImpl<Sucursal, SucursalDto, Long> implements ISucursalFacade {

    private final ISucursalService sucursalService;

    public SucursalFacadeImpl(SucursalServiceImpl sucursalService, ISucursalMapper baseMapper) {
        super(sucursalService, baseMapper);
        this.sucursalService = sucursalService;
    }


    public List<SucursalDto> findSucursalesByEmpresaId(Long id) {
        return this.baseMapper.toDTOsList(this.sucursalService.findSucursalesByEmpresaId(id));
    }
}
