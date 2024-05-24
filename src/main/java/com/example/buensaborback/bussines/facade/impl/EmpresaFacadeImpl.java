package com.example.buensaborback.bussines.facade.impl;

import com.example.buensaborback.bussines.facade.IEmpresaFacade;
import com.example.buensaborback.bussines.mappers.IEmpresaMapper;
import com.example.buensaborback.bussines.service.impl.EmpresaServiceImpl;
import com.example.buensaborback.domain.dtos.empresa.EmpresaDto;
import com.example.buensaborback.domain.entities.Empresa;
import org.springframework.stereotype.Service;

@Service
public class EmpresaFacadeImpl  extends BaseFacadeImpl<Empresa, EmpresaDto, Long> implements IEmpresaFacade {

    public EmpresaFacadeImpl(EmpresaServiceImpl baseService, IEmpresaMapper baseMapper) {
        super(baseService, baseMapper);
    }

}
