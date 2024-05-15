package com.example.buensaborback.bussines.facade.impl;

import com.example.buensaborback.bussines.facade.ICategoriaFacade;
import com.example.buensaborback.bussines.mappers.ICategoriaMapper;
import com.example.buensaborback.bussines.service.impl.CategoriaServiceImpl;
import com.example.buensaborback.domain.dtos.categoria.CategoriaDto;
import com.example.buensaborback.domain.entities.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaFacadeImpl extends BaseFacadeImpl<Categoria, CategoriaDto, Long> implements ICategoriaFacade {

    @Autowired
    public CategoriaFacadeImpl(CategoriaServiceImpl baseService, ICategoriaMapper baseMapper) {
        super(baseService, baseMapper);
    }
}
