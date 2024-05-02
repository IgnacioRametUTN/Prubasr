package com.example.buensaborback.bussines.service;

import com.example.buensaborback.domain.entities.Base;

import java.io.Serializable;
import java.util.List;

public interface IBaseService <E extends Base, ID extends Serializable> {
    E create(E user);
    E update(E user);
    void delete(ID id);
    E getById(ID id);
    List<E> getAll();

}