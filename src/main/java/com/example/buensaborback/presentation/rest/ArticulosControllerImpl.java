package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.bussines.facade.impl.BaseArticuloFacadeImpl;
import com.example.buensaborback.bussines.facade.impl.BaseFacadeImpl;
import com.example.buensaborback.domain.dtos.BaseDto;
import com.example.buensaborback.domain.entities.Articulo;
import com.example.buensaborback.domain.entities.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.io.Serializable;

public abstract class ArticulosControllerImpl<E extends Articulo,D extends BaseDto, ID extends Serializable, F extends BaseFacadeImpl<E,D,ID>> implements IGenericController<D,ID> {
    private static final Logger logger = LoggerFactory.getLogger(ArticulosControllerImpl.class);
    protected F facade;
    public ArticulosControllerImpl(F facade){
        this.facade = facade;
    }


    @Override
    @GetMapping
    public ResponseEntity<?> getAll() {
        logger.info("INICIO GET ALL");
        return ResponseEntity.ok().body(this.facade.getAll());
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(ID id) {
        logger.info("INICIO GET BY ID {}",id);
        return null;
    }

    @Override
    @PostMapping()
    public ResponseEntity<?> save(D entity) {
        logger.info("INICIO CREATE {}",entity.getClass());
        return null;
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> update(ID id, D entity) {
        logger.info("INICIO EDIT {}",entity.getClass());
        return null;
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(ID id) {
        logger.info("INICIO DELETE BY ID {}",id);
        return null;
    }


}
