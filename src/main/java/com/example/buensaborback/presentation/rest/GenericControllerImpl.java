package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.bussines.facade.impl.BaseFacadeImpl;
import com.example.buensaborback.domain.dtos.BaseDto;
import com.example.buensaborback.domain.entities.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Optional;

public abstract class GenericControllerImpl<E extends Base,D extends BaseDto, ID extends Serializable, F extends BaseFacadeImpl<E,D,ID>> implements IGenericController<D,ID> {
    private static final Logger logger = LoggerFactory.getLogger(GenericControllerImpl.class);
    protected F facade;
    public GenericControllerImpl(F facade){
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
        return ResponseEntity.ok().body(this.facade.getById(id));
    }

    @Override
    @PostMapping()
    public ResponseEntity<?> save(D entity) {
        logger.info("INICIO CREATE {}",entity.getClass());
        D savedEntity = this.facade.create(entity);
        return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<?> update(ID id, D entity) {
        logger.info("INICIO EDIT {}",entity.getClass());
        D savedEntity = this.facade.update(entity, id);
        return new ResponseEntity<>(savedEntity, HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(ID id) {
        logger.info("INICIO DELETE BY ID {}",id);
        this.facade.delete(id);
        return ResponseEntity.ok().build();
    }

}
