package com.example.buensaborback.bussines.mappers;


import com.example.buensaborback.domain.dtos.empresa.EmpresaDto;
import com.example.buensaborback.domain.entities.Empresa;
import org.mapstruct.Mapper;
import org.mapstruct.MappingInheritanceStrategy;

@Mapper(componentModel = "spring", uses = IDomicilioMapper.class, mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG)
public interface IEmpresaMapper extends IBaseMapper<Empresa, EmpresaDto> {
}
