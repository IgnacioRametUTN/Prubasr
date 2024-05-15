package com.example.buensaborback.bussines.facade.impl;

import com.example.buensaborback.bussines.facade.IArticuloInsumoFacade;
import com.example.buensaborback.bussines.mappers.IArticuloInsumoMapper;
import com.example.buensaborback.bussines.service.impl.ArticuloInsumoServiceImpl;
import com.example.buensaborback.domain.dtos.articulos.insumo.ArticuloInsumoDto;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticuloInsumoFacadeImpl extends BaseFacadeImpl<ArticuloInsumo, ArticuloInsumoDto, Long> implements IArticuloInsumoFacade {

    @Autowired
    public ArticuloInsumoFacadeImpl(ArticuloInsumoServiceImpl baseService, IArticuloInsumoMapper baseMapper) {
        super(baseService, baseMapper);
    }
}
