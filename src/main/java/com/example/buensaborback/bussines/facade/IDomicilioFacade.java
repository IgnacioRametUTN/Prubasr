package com.example.buensaborback.bussines.facade;

import com.example.buensaborback.domain.dtos.domicilio.DomicilioFullDto;
import com.example.buensaborback.domain.dtos.domicilio.DomicilioShortDto;
import com.example.buensaborback.domain.entities.Domicilio;

public interface IDomicilioFacade {
    DomicilioFullDto save(DomicilioShortDto domicilio);

    DomicilioFullDto findById(Long id);
     DomicilioShortDto findShortById(Long id);
}