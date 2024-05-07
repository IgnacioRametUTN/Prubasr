package com.example.buensaborback.bussines.facade.impl;

import com.example.buensaborback.bussines.facade.IBaseFacade;
import com.example.buensaborback.bussines.mappers.IBaseMapper;
import com.example.buensaborback.bussines.service.IBaseService;
import com.example.buensaborback.domain.dtos.BaseDto;
import com.example.buensaborback.domain.entities.Base;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public abstract class BaseArticuloFacadeImpl<E extends Base,D extends BaseDto,ID extends Serializable> implements IBaseFacade<D,ID> {

    protected IBaseService<E,ID> baseService;
    protected IBaseMapper<E,D> baseMapper;

    @Autowired
    public BaseArticuloFacadeImpl(IBaseService<E,ID> baseService, IBaseMapper<E,D> baseMapper) {
        this.baseService = baseService;
        this.baseMapper = baseMapper;
    }

    @Override
    public D create(D request){
        // Convierte a entidad
        var entityToCreate = baseMapper.toEntity(request);
        // Graba una entidad
        var entityCreated = baseService.create(entityToCreate);
        // convierte a Dto para devolver
        return baseMapper.toDTO(entityCreated);
    }

    @Override
    public D getById(ID id){
        // Busca una entidad por id
        var entity = baseService.getById(id);
        // convierte la entidad a DTO
        return baseMapper.toDTO(entity);
    }

    @Override
    public List<D> getAll(){
        // trae una lista de entidades
        var entities = baseService.getAll();
        //  devuelve una lista de DTO
        return entities
                .stream()
                .map(baseMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(ID id){
        var entity = baseService.getById(id);
        baseService.delete(id);
    }

    @Override
    public D update(D request, ID id){
        var entityToUpdate = baseMapper.toEntity(request);
        var entityUpdated = baseService.update(entityToUpdate);
        return baseMapper.toDTO(entityUpdated);
    }

}
