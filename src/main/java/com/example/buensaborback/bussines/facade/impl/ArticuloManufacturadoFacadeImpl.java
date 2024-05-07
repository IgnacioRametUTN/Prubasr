package com.example.buensaborback.bussines.facade.impl;

import com.example.buensaborback.bussines.facade.IArticuloManufacturadoFacade;
import com.example.buensaborback.bussines.mappers.IArticuloManufacturadoMapper;
import com.example.buensaborback.bussines.service.impl.ArticuloManufacturadoServiceImpl;
import com.example.buensaborback.domain.dtos.articulos.manufacturado.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticuloManufacturadoFacadeImpl extends BaseFacadeImpl<ArticuloManufacturado, ArticuloManufacturadoDto, Long> implements IArticuloManufacturadoFacade {

    @Autowired
    public ArticuloManufacturadoFacadeImpl(ArticuloManufacturadoServiceImpl baseService, IArticuloManufacturadoMapper baseMapper) {
        super(baseService, baseMapper);
    }
}
