package com.example.buensaborback.bussines.mappers;

import com.example.buensaborback.domain.dtos.BaseDto;
import com.example.buensaborback.domain.entities.Articulo;
import com.example.buensaborback.domain.entities.Base;
import org.springframework.context.annotation.Configuration;

import java.util.List;
@Configuration
public interface IBaseArticuloMapper <E extends Articulo,D extends BaseDto>{
    D toDTO(E source);
    E toEntity(D source);
    List<D> toDTOsList(List<E> source);
    List<E> toEntitiesList(List<D> source);
}