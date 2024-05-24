package com.example.buensaborback.bussines.facade.impl;

import com.example.buensaborback.bussines.facade.IArticuloManufacturadoFacade;
import com.example.buensaborback.bussines.mappers.IArticuloManufacturadoMapper;
import com.example.buensaborback.bussines.service.IArticuloInsumoService;
import com.example.buensaborback.bussines.service.IArticuloManufacturadoService;
import com.example.buensaborback.bussines.service.impl.ArticuloManufacturadoServiceImpl;
import com.example.buensaborback.domain.dtos.articulos.manufacturado.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticuloManufacturadoFacadeImpl extends BaseFacadeImpl<ArticuloManufacturado, ArticuloManufacturadoDto, Long> implements IArticuloManufacturadoFacade {

    private final IArticuloManufacturadoService articuloManufacturadoService;
    @Autowired
    public ArticuloManufacturadoFacadeImpl(ArticuloManufacturadoServiceImpl baseService, IArticuloManufacturadoMapper baseMapper) {
        super(baseService, baseMapper);
        this.articuloManufacturadoService = baseService;
    }

    @Override
    public List<ArticuloManufacturadoDto> getArticulosInsumos(Optional<Long> categoria, Optional<Long> unidadMedida, Optional<String> denominacion) {
        return this.baseMapper.toDTOsList(this.articuloManufacturadoService.getAll(categoria, unidadMedida, denominacion));
    }
}
