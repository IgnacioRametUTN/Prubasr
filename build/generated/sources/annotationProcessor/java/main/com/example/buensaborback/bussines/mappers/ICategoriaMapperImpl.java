package com.example.buensaborback.bussines.mappers;

import com.example.buensaborback.domain.dtos.categoria.CategoriaDto;
import com.example.buensaborback.domain.entities.Categoria;
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
public class ICategoriaMapperImpl implements ICategoriaMapper {

    @Override
    public CategoriaDto toDTO(Categoria source) {
        if ( source == null ) {
            return null;
        }

        CategoriaDto.CategoriaDtoBuilder<?, ?> categoriaDto = CategoriaDto.builder();

        categoriaDto.id( source.getId() );
        categoriaDto.alta( source.isAlta() );
        categoriaDto.denominacion( source.getDenominacion() );

        return categoriaDto.build();
    }

    @Override
    public Categoria toEntity(CategoriaDto source) {
        if ( source == null ) {
            return null;
        }

        Categoria.CategoriaBuilder<?, ?> categoria = Categoria.builder();

        categoria.id( source.getId() );
        if ( source.getAlta() != null ) {
            categoria.alta( source.getAlta() );
        }
        categoria.denominacion( source.getDenominacion() );

        return categoria.build();
    }

    @Override
    public List<CategoriaDto> toDTOsList(List<Categoria> source) {
        if ( source == null ) {
            return null;
        }

        List<CategoriaDto> list = new ArrayList<CategoriaDto>( source.size() );
        for ( Categoria categoria : source ) {
            list.add( toDTO( categoria ) );
        }

        return list;
    }

    @Override
    public List<Categoria> toEntitiesList(List<CategoriaDto> source) {
        if ( source == null ) {
            return null;
        }

        List<Categoria> list = new ArrayList<Categoria>( source.size() );
        for ( CategoriaDto categoriaDto : source ) {
            list.add( toEntity( categoriaDto ) );
        }

        return list;
    }
}
