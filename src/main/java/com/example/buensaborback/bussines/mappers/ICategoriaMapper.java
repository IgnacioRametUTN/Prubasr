package com.example.buensaborback.bussines.mappers;

import com.example.buensaborback.domain.dtos.articulos.insumo.ArticuloInsumoDto;
import com.example.buensaborback.domain.dtos.categoria.CategoriaDto;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.domain.entities.Categoria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICategoriaMapper  extends IBaseMapper<Categoria, CategoriaDto> {
}
