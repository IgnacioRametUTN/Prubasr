package com.example.buensaborback.bussines.facade;

import com.example.buensaborback.domain.dtos.BaseDto;

import java.io.Serializable;
import java.util.List;


public interface IBaseFacade <D extends BaseDto, ID extends Serializable> {
    D create(D request);
    D update(D request, ID id);
    void delete(ID id);
    D getById(ID id);

    List<D> getAll();
}
