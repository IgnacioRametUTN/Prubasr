package com.example.buensaborback.bussines.facade.impl;

import com.example.buensaborback.bussines.facade.IUnidadMedidaFacade;
import com.example.buensaborback.bussines.mappers.IUnidadMedidaMapper;
import com.example.buensaborback.bussines.service.impl.UnidadMedidaServiceImpl;
import com.example.buensaborback.domain.dtos.unidadmedida.UnidadMedidaDto;
import com.example.buensaborback.domain.entities.UnidadMedida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnidadMedidaFacadeImpl  extends BaseFacadeImpl<UnidadMedida, UnidadMedidaDto, Long> implements IUnidadMedidaFacade {

    @Autowired
    public UnidadMedidaFacadeImpl(UnidadMedidaServiceImpl baseService, IUnidadMedidaMapper baseMapper) {
        super(baseService, baseMapper);
    }
}
