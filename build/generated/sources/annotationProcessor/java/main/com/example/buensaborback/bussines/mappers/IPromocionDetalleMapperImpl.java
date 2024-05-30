package com.example.buensaborback.bussines.mappers;

import com.example.buensaborback.domain.dtos.promocion.PromocionDetalleDto;
import com.example.buensaborback.domain.entities.PromocionDetalle;
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
public class IPromocionDetalleMapperImpl implements IPromocionDetalleMapper {

    @Override
    public PromocionDetalleDto toDTO(PromocionDetalle promocionDetalle) {
        if ( promocionDetalle == null ) {
            return null;
        }

        PromocionDetalleDto.PromocionDetalleDtoBuilder<?, ?> promocionDetalleDto = PromocionDetalleDto.builder();

        promocionDetalleDto.id( promocionDetalle.getId() );
        promocionDetalleDto.alta( promocionDetalle.isAlta() );
        promocionDetalleDto.cantidad( promocionDetalle.getCantidad() );

        return promocionDetalleDto.build();
    }

    @Override
    public PromocionDetalle toEntity(PromocionDetalleDto promocionDetalleDto) {
        if ( promocionDetalleDto == null ) {
            return null;
        }

        PromocionDetalle.PromocionDetalleBuilder<?, ?> promocionDetalle = PromocionDetalle.builder();

        promocionDetalle.id( promocionDetalleDto.getId() );
        if ( promocionDetalleDto.getAlta() != null ) {
            promocionDetalle.alta( promocionDetalleDto.getAlta() );
        }
        promocionDetalle.cantidad( promocionDetalleDto.getCantidad() );

        return promocionDetalle.build();
    }

    @Override
    public String map(PromocionDetalleDto value) {
        if ( value == null ) {
            return null;
        }

        String string = new String();

        return string;
    }

    @Override
    public List<PromocionDetalleDto> toDTOsList(List<PromocionDetalle> source) {
        if ( source == null ) {
            return null;
        }

        List<PromocionDetalleDto> list = new ArrayList<PromocionDetalleDto>( source.size() );
        for ( PromocionDetalle promocionDetalle : source ) {
            list.add( toDTO( promocionDetalle ) );
        }

        return list;
    }

    @Override
    public List<PromocionDetalle> toEntitiesList(List<PromocionDetalleDto> source) {
        if ( source == null ) {
            return null;
        }

        List<PromocionDetalle> list = new ArrayList<PromocionDetalle>( source.size() );
        for ( PromocionDetalleDto promocionDetalleDto : source ) {
            list.add( toEntity( promocionDetalleDto ) );
        }

        return list;
    }
}
