package com.example.buensaborback.bussines.facade.impl;

import com.example.buensaborback.bussines.facade.IDomicilioFacade;
import com.example.buensaborback.bussines.mappers.IBaseMapper;
import com.example.buensaborback.bussines.mappers.IDomicilioMapper;
import com.example.buensaborback.bussines.service.IDomicilioService;
import com.example.buensaborback.domain.dtos.domicilio.DomicilioFullDto;
import com.example.buensaborback.domain.entities.Domicilio;
import org.springframework.stereotype.Service;

@Service
public class DomicilioFacadaImpl extends BaseFacadeImpl<Domicilio, DomicilioFullDto, Long> implements IDomicilioFacade {


    public DomicilioFacadaImpl(IDomicilioService baseService, IDomicilioMapper baseMapper) {
        super(baseService, baseMapper);
    }
}
