package com.example.buensaborback.bussines.mappers;

import com.example.buensaborback.domain.dtos.sucursal.SucursalDto;
import com.example.buensaborback.domain.entities.Sucursal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISucursalMapper  extends IBaseMapper<Sucursal, SucursalDto> {
}
