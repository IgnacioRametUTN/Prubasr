package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.domain.dtos.BaseDto;
import com.example.buensaborback.domain.entities.Base;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;

public interface IGenericController<T extends BaseDto,ID extends Serializable> {

    ResponseEntity<?> getAll();
    ResponseEntity<?> getById(@PathVariable ID id);
    ResponseEntity<?> save(@RequestBody T t);
    ResponseEntity<?> update(@PathVariable ID id,@RequestBody T t);
    ResponseEntity<?> delete(@PathVariable ID id);
}
