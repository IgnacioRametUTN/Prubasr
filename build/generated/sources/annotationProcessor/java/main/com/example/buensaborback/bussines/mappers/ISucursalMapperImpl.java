package com.example.buensaborback.bussines.mappers;

import com.example.buensaborback.domain.dtos.empresa.EmpresaDto;
import com.example.buensaborback.domain.dtos.sucursal.SucursalDto;
import com.example.buensaborback.domain.entities.Empresa;
import com.example.buensaborback.domain.entities.Sucursal;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
public class ISucursalMapperImpl implements ISucursalMapper {

    @Override
    public SucursalDto toDTO(Sucursal source) {
        if ( source == null ) {
            return null;
        }

        SucursalDto.SucursalDtoBuilder<?, ?> sucursalDto = SucursalDto.builder();

        sucursalDto.id( source.getId() );
        sucursalDto.alta( source.isAlta() );
        sucursalDto.nombre( source.getNombre() );
        if ( source.getHorarioApertura() != null ) {
            sucursalDto.horarioApertura( DateTimeFormatter.ISO_LOCAL_TIME.format( source.getHorarioApertura() ) );
        }
        if ( source.getHorarioCierre() != null ) {
            sucursalDto.horarioCierre( DateTimeFormatter.ISO_LOCAL_TIME.format( source.getHorarioCierre() ) );
        }
        sucursalDto.empresa( empresaToEmpresaDto( source.getEmpresa() ) );

        return sucursalDto.build();
    }

    @Override
    public Sucursal toEntity(SucursalDto source) {
        if ( source == null ) {
            return null;
        }

        Sucursal.SucursalBuilder<?, ?> sucursal = Sucursal.builder();

        sucursal.id( source.getId() );
        if ( source.getAlta() != null ) {
            sucursal.alta( source.getAlta() );
        }
        sucursal.nombre( source.getNombre() );
        if ( source.getHorarioApertura() != null ) {
            sucursal.horarioApertura( LocalTime.parse( source.getHorarioApertura() ) );
        }
        if ( source.getHorarioCierre() != null ) {
            sucursal.horarioCierre( LocalTime.parse( source.getHorarioCierre() ) );
        }
        sucursal.empresa( empresaDtoToEmpresa( source.getEmpresa() ) );

        return sucursal.build();
    }

    @Override
    public List<SucursalDto> toDTOsList(List<Sucursal> source) {
        if ( source == null ) {
            return null;
        }

        List<SucursalDto> list = new ArrayList<SucursalDto>( source.size() );
        for ( Sucursal sucursal : source ) {
            list.add( toDTO( sucursal ) );
        }

        return list;
    }

    @Override
    public List<Sucursal> toEntitiesList(List<SucursalDto> source) {
        if ( source == null ) {
            return null;
        }

        List<Sucursal> list = new ArrayList<Sucursal>( source.size() );
        for ( SucursalDto sucursalDto : source ) {
            list.add( toEntity( sucursalDto ) );
        }

        return list;
    }

    protected EmpresaDto empresaToEmpresaDto(Empresa empresa) {
        if ( empresa == null ) {
            return null;
        }

        EmpresaDto.EmpresaDtoBuilder<?, ?> empresaDto = EmpresaDto.builder();

        empresaDto.id( empresa.getId() );
        empresaDto.alta( empresa.isAlta() );
        empresaDto.nombre( empresa.getNombre() );
        empresaDto.razonSocial( empresa.getRazonSocial() );
        empresaDto.cuil( empresa.getCuil() );

        return empresaDto.build();
    }

    protected Empresa empresaDtoToEmpresa(EmpresaDto empresaDto) {
        if ( empresaDto == null ) {
            return null;
        }

        Empresa.EmpresaBuilder<?, ?> empresa = Empresa.builder();

        empresa.id( empresaDto.getId() );
        if ( empresaDto.getAlta() != null ) {
            empresa.alta( empresaDto.getAlta() );
        }
        empresa.nombre( empresaDto.getNombre() );
        empresa.razonSocial( empresaDto.getRazonSocial() );
        empresa.cuil( empresaDto.getCuil() );

        return empresa.build();
    }
}
