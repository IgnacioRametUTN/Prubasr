package com.example.buensaborback.bussines.facade.impl;

import com.example.buensaborback.bussines.facade.IDomicilioFacade;
import com.example.buensaborback.bussines.mappers.IDomicilioMapper;
import com.example.buensaborback.domain.dtos.domicilio.DomicilioFullDto;
import com.example.buensaborback.domain.dtos.domicilio.DomicilioShortDto;
import com.example.buensaborback.domain.entities.Domicilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DomicilioFacadaImpl implements IDomicilioFacade {

    IDomicilioMapper domicilioMapper;

    public DomicilioFacadaImpl(IDomicilioMapper domicilioMapper){
        this.domicilioMapper = domicilioMapper;
    }


    @Override
    public DomicilioFullDto save(DomicilioShortDto domicilio) {
        return null;
    }

    @Override
    public DomicilioFullDto findById(Long id) {
        return null;
    }

    @Override
    public DomicilioShortDto findShortById(Long id) {
        return null;
    }
}
