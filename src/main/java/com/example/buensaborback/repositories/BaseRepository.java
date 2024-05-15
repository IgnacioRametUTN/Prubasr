package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Base;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

@Repository
public interface BaseRepository <E extends Base, ID extends Serializable> extends JpaRepository<E, ID> {
    Logger logger = LoggerFactory.getLogger(BaseRepository.class);

    @Override
    @Transactional
    default void delete(E entity) {
        logger.info("EJECUTANDO DELETE SOBREESCRITO");
        entity.setAlta(false);
        save(entity);
    }

    @Override
    default E getById(ID id){
        logger.info("EJECUTANDO GET BY ID SOBREESCRITO");
        var optionalEntity = findById(id);

        if (optionalEntity.isEmpty()){
            String errMsg = "La entidad "+  optionalEntity.getClass().getSimpleName() +" con el id " + id + " no se encuentra";
            logger.error(errMsg);
            throw new NotFoundException(errMsg);
        }

        var entity = optionalEntity.get();
        logger.info("Obtenida entidad alta {}",entity.isAlta());
        if(!entity.isAlta()){
            String errMsg = "La entidad del tipo " + entity.getClass().getSimpleName() + " con el id " + id + " se encuentra borrada logicamente";
            logger.error(errMsg);
            throw new RuntimeException(errMsg);
        }
        return entity;
    }

    default List<E> getAll(){
        logger.info("EJECUTANDO GET ALL PERSONALIZADO");
        return findAll().stream().filter(Base::isAlta).toList();
    }

}
