package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Articulo;
import com.example.buensaborback.domain.entities.Base;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;

//Se debe crear repositorio para articulos porque no se puede cambiar la estrategia de Base (Identity) por (Sequence) que se necesita
// para poder crear ArticuloInsumo y ArticuloManufacturado con estrategia TABLE_PER_CLASS
public interface BaseArticuloRepository <E extends Articulo, ID extends Serializable> extends JpaRepository<E, ID> {
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
        logger.info("EJECUTANDO GEY BY ID SOBREESCRITO");
        var optionalEntity = findById(id);

        if (optionalEntity.isEmpty()){
            String errMsg = "La entidad con el id " + id + " se encuentra borrada logicamente";
            logger.error(errMsg);
            throw new RuntimeException(errMsg);
        }

        var entity = optionalEntity.get();
        if(!entity.isAlta()){
            String errMsg = "La entidad del tipo " + entity.getClass().getSimpleName() + " con el id " + id + " se encuentra borrada logicamente";
            logger.error(errMsg);
            throw new RuntimeException(errMsg);
        }
        return entity;
    }

    default List<E> getAll(){
        logger.info("EJECUTANDO GET ALL PERSONALIZADO");
        return findAll().stream().filter(Articulo::isAlta).toList();
    }

}