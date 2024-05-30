package com.example.buensaborback.bussines.mappers;

import com.example.buensaborback.domain.dtos.empresa.EmpresaDto;
import com.example.buensaborback.domain.entities.Empresa;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-30T19:18:28-0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class IEmpresaMapperImpl implements IEmpresaMapper {

    @Override
    public EmpresaDto toDTO(Empresa source) {
        if ( source == null ) {
            return null;
        }

        EmpresaDto.EmpresaDtoBuilder<?, ?> empresaDto = EmpresaDto.builder();

        empresaDto.id( source.getId() );
        empresaDto.alta( source.isAlta() );
        empresaDto.nombre( source.getNombre() );
        empresaDto.razonSocial( source.getRazonSocial() );
        empresaDto.cuil( source.getCuil() );

        return empresaDto.build();
    }

    @Override
    public Empresa toEntity(EmpresaDto source) {
        if ( source == null ) {
            return null;
        }

        Empresa.EmpresaBuilder<?, ?> empresa = Empresa.builder();

        empresa.id( source.getId() );
        if ( source.getAlta() != null ) {
            empresa.alta( source.getAlta() );
        }
        empresa.nombre( source.getNombre() );
        empresa.razonSocial( source.getRazonSocial() );
        empresa.cuil( source.getCuil() );

        return empresa.build();
    }

    @Override
    public List<EmpresaDto> toDTOsList(List<Empresa> source) {
        if ( source == null ) {
            return null;
        }

        List<EmpresaDto> list = new ArrayList<EmpresaDto>( source.size() );
        for ( Empresa empresa : source ) {
            list.add( toDTO( empresa ) );
        }

        return list;
    }

    @Override
    public List<Empresa> toEntitiesList(List<EmpresaDto> source) {
        if ( source == null ) {
            return null;
        }

        List<Empresa> list = new ArrayList<Empresa>( source.size() );
        for ( EmpresaDto empresaDto : source ) {
            list.add( toEntity( empresaDto ) );
        }

        return list;
    }
}
