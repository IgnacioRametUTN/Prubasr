package com.example.buensaborback.bussines.mappers;

import com.example.buensaborback.domain.dtos.ImagenDto;
import com.example.buensaborback.domain.dtos.articulos.manufacturado.ArticuloManufacturadoDetalleDto;
import com.example.buensaborback.domain.dtos.articulos.manufacturado.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.dtos.categoria.CategoriaDto;
import com.example.buensaborback.domain.dtos.promocion.PromocionDetalleDto;
import com.example.buensaborback.domain.dtos.unidadmedida.UnidadMedidaDto;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.domain.entities.ArticuloManufacturadoDetalle;
import com.example.buensaborback.domain.entities.Categoria;
import com.example.buensaborback.domain.entities.Imagen;
import com.example.buensaborback.domain.entities.PromocionDetalle;
import com.example.buensaborback.domain.entities.UnidadMedida;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-30T19:18:28-0300",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class IArticuloManufacturadoMapperImpl implements IArticuloManufacturadoMapper {

    @Autowired
    private IPromocionDetalleMapper iPromocionDetalleMapper;
    @Autowired
    private IArticuloInsumoMapper iArticuloInsumoMapper;

    @Override
    public ArticuloManufacturadoDto toDTO(ArticuloManufacturado source) {
        if ( source == null ) {
            return null;
        }

        ArticuloManufacturadoDto.ArticuloManufacturadoDtoBuilder<?, ?> articuloManufacturadoDto = ArticuloManufacturadoDto.builder();

        articuloManufacturadoDto.id( source.getId() );
        articuloManufacturadoDto.alta( source.isAlta() );
        articuloManufacturadoDto.denominacion( source.getDenominacion() );
        articuloManufacturadoDto.precioVenta( source.getPrecioVenta() );
        articuloManufacturadoDto.imagenes( imagenSetToImagenDtoSet( source.getImagenes() ) );
        articuloManufacturadoDto.unidadMedida( unidadMedidaToUnidadMedidaDto( source.getUnidadMedida() ) );
        articuloManufacturadoDto.categoria( categoriaToCategoriaDto( source.getCategoria() ) );
        articuloManufacturadoDto.promocionDetalle( promocionDetalleSetToPromocionDetalleDtoSet( source.getPromocionDetalle() ) );
        articuloManufacturadoDto.descripcion( source.getDescripcion() );
        articuloManufacturadoDto.tiempoEstimadoMinutos( source.getTiempoEstimadoMinutos() );
        articuloManufacturadoDto.preparacion( source.getPreparacion() );
        articuloManufacturadoDto.articuloManufacturadoDetalles( articuloManufacturadoDetalleSetToArticuloManufacturadoDetalleDtoSet( source.getArticuloManufacturadoDetalles() ) );

        return articuloManufacturadoDto.build();
    }

    @Override
    public ArticuloManufacturado toEntity(ArticuloManufacturadoDto source) {
        if ( source == null ) {
            return null;
        }

        ArticuloManufacturado.ArticuloManufacturadoBuilder<?, ?> articuloManufacturado = ArticuloManufacturado.builder();

        articuloManufacturado.id( source.getId() );
        if ( source.getAlta() != null ) {
            articuloManufacturado.alta( source.getAlta() );
        }
        articuloManufacturado.denominacion( source.getDenominacion() );
        articuloManufacturado.precioVenta( source.getPrecioVenta() );
        articuloManufacturado.imagenes( imagenDtoSetToImagenSet( source.getImagenes() ) );
        articuloManufacturado.unidadMedida( unidadMedidaDtoToUnidadMedida( source.getUnidadMedida() ) );
        articuloManufacturado.categoria( categoriaDtoToCategoria( source.getCategoria() ) );
        articuloManufacturado.promocionDetalle( promocionDetalleDtoSetToPromocionDetalleSet( source.getPromocionDetalle() ) );
        articuloManufacturado.descripcion( source.getDescripcion() );
        articuloManufacturado.tiempoEstimadoMinutos( source.getTiempoEstimadoMinutos() );
        articuloManufacturado.preparacion( source.getPreparacion() );
        articuloManufacturado.articuloManufacturadoDetalles( articuloManufacturadoDetalleDtoSetToArticuloManufacturadoDetalleSet( source.getArticuloManufacturadoDetalles() ) );

        return articuloManufacturado.build();
    }

    @Override
    public List<ArticuloManufacturadoDto> toDTOsList(List<ArticuloManufacturado> source) {
        if ( source == null ) {
            return null;
        }

        List<ArticuloManufacturadoDto> list = new ArrayList<ArticuloManufacturadoDto>( source.size() );
        for ( ArticuloManufacturado articuloManufacturado : source ) {
            list.add( toDTO( articuloManufacturado ) );
        }

        return list;
    }

    @Override
    public List<ArticuloManufacturado> toEntitiesList(List<ArticuloManufacturadoDto> source) {
        if ( source == null ) {
            return null;
        }

        List<ArticuloManufacturado> list = new ArrayList<ArticuloManufacturado>( source.size() );
        for ( ArticuloManufacturadoDto articuloManufacturadoDto : source ) {
            list.add( toEntity( articuloManufacturadoDto ) );
        }

        return list;
    }

    protected ImagenDto imagenToImagenDto(Imagen imagen) {
        if ( imagen == null ) {
            return null;
        }

        ImagenDto.ImagenDtoBuilder<?, ?> imagenDto = ImagenDto.builder();

        imagenDto.id( imagen.getId() );
        imagenDto.alta( imagen.isAlta() );
        imagenDto.url( imagen.getUrl() );

        return imagenDto.build();
    }

    protected Set<ImagenDto> imagenSetToImagenDtoSet(Set<Imagen> set) {
        if ( set == null ) {
            return null;
        }

        Set<ImagenDto> set1 = new LinkedHashSet<ImagenDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Imagen imagen : set ) {
            set1.add( imagenToImagenDto( imagen ) );
        }

        return set1;
    }

    protected UnidadMedidaDto unidadMedidaToUnidadMedidaDto(UnidadMedida unidadMedida) {
        if ( unidadMedida == null ) {
            return null;
        }

        UnidadMedidaDto.UnidadMedidaDtoBuilder<?, ?> unidadMedidaDto = UnidadMedidaDto.builder();

        unidadMedidaDto.id( unidadMedida.getId() );
        unidadMedidaDto.alta( unidadMedida.isAlta() );
        unidadMedidaDto.denominacion( unidadMedida.getDenominacion() );

        return unidadMedidaDto.build();
    }

    protected CategoriaDto categoriaToCategoriaDto(Categoria categoria) {
        if ( categoria == null ) {
            return null;
        }

        CategoriaDto.CategoriaDtoBuilder<?, ?> categoriaDto = CategoriaDto.builder();

        categoriaDto.id( categoria.getId() );
        categoriaDto.alta( categoria.isAlta() );
        categoriaDto.denominacion( categoria.getDenominacion() );

        return categoriaDto.build();
    }

    protected Set<PromocionDetalleDto> promocionDetalleSetToPromocionDetalleDtoSet(Set<PromocionDetalle> set) {
        if ( set == null ) {
            return null;
        }

        Set<PromocionDetalleDto> set1 = new LinkedHashSet<PromocionDetalleDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( PromocionDetalle promocionDetalle : set ) {
            set1.add( iPromocionDetalleMapper.toDTO( promocionDetalle ) );
        }

        return set1;
    }

    protected ArticuloManufacturadoDetalleDto articuloManufacturadoDetalleToArticuloManufacturadoDetalleDto(ArticuloManufacturadoDetalle articuloManufacturadoDetalle) {
        if ( articuloManufacturadoDetalle == null ) {
            return null;
        }

        ArticuloManufacturadoDetalleDto.ArticuloManufacturadoDetalleDtoBuilder<?, ?> articuloManufacturadoDetalleDto = ArticuloManufacturadoDetalleDto.builder();

        articuloManufacturadoDetalleDto.id( articuloManufacturadoDetalle.getId() );
        articuloManufacturadoDetalleDto.alta( articuloManufacturadoDetalle.isAlta() );
        articuloManufacturadoDetalleDto.cantidad( articuloManufacturadoDetalle.getCantidad() );
        articuloManufacturadoDetalleDto.articuloInsumo( iArticuloInsumoMapper.toDTO( articuloManufacturadoDetalle.getArticuloInsumo() ) );

        return articuloManufacturadoDetalleDto.build();
    }

    protected Set<ArticuloManufacturadoDetalleDto> articuloManufacturadoDetalleSetToArticuloManufacturadoDetalleDtoSet(Set<ArticuloManufacturadoDetalle> set) {
        if ( set == null ) {
            return null;
        }

        Set<ArticuloManufacturadoDetalleDto> set1 = new LinkedHashSet<ArticuloManufacturadoDetalleDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ArticuloManufacturadoDetalle articuloManufacturadoDetalle : set ) {
            set1.add( articuloManufacturadoDetalleToArticuloManufacturadoDetalleDto( articuloManufacturadoDetalle ) );
        }

        return set1;
    }

    protected Imagen imagenDtoToImagen(ImagenDto imagenDto) {
        if ( imagenDto == null ) {
            return null;
        }

        Imagen.ImagenBuilder<?, ?> imagen = Imagen.builder();

        imagen.id( imagenDto.getId() );
        if ( imagenDto.getAlta() != null ) {
            imagen.alta( imagenDto.getAlta() );
        }
        imagen.url( imagenDto.getUrl() );

        return imagen.build();
    }

    protected Set<Imagen> imagenDtoSetToImagenSet(Set<ImagenDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<Imagen> set1 = new LinkedHashSet<Imagen>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ImagenDto imagenDto : set ) {
            set1.add( imagenDtoToImagen( imagenDto ) );
        }

        return set1;
    }

    protected UnidadMedida unidadMedidaDtoToUnidadMedida(UnidadMedidaDto unidadMedidaDto) {
        if ( unidadMedidaDto == null ) {
            return null;
        }

        UnidadMedida.UnidadMedidaBuilder<?, ?> unidadMedida = UnidadMedida.builder();

        unidadMedida.id( unidadMedidaDto.getId() );
        if ( unidadMedidaDto.getAlta() != null ) {
            unidadMedida.alta( unidadMedidaDto.getAlta() );
        }
        unidadMedida.denominacion( unidadMedidaDto.getDenominacion() );

        return unidadMedida.build();
    }

    protected Categoria categoriaDtoToCategoria(CategoriaDto categoriaDto) {
        if ( categoriaDto == null ) {
            return null;
        }

        Categoria.CategoriaBuilder<?, ?> categoria = Categoria.builder();

        categoria.id( categoriaDto.getId() );
        if ( categoriaDto.getAlta() != null ) {
            categoria.alta( categoriaDto.getAlta() );
        }
        categoria.denominacion( categoriaDto.getDenominacion() );

        return categoria.build();
    }

    protected Set<PromocionDetalle> promocionDetalleDtoSetToPromocionDetalleSet(Set<PromocionDetalleDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<PromocionDetalle> set1 = new LinkedHashSet<PromocionDetalle>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( PromocionDetalleDto promocionDetalleDto : set ) {
            set1.add( iPromocionDetalleMapper.toEntity( promocionDetalleDto ) );
        }

        return set1;
    }

    protected ArticuloManufacturadoDetalle articuloManufacturadoDetalleDtoToArticuloManufacturadoDetalle(ArticuloManufacturadoDetalleDto articuloManufacturadoDetalleDto) {
        if ( articuloManufacturadoDetalleDto == null ) {
            return null;
        }

        ArticuloManufacturadoDetalle.ArticuloManufacturadoDetalleBuilder<?, ?> articuloManufacturadoDetalle = ArticuloManufacturadoDetalle.builder();

        articuloManufacturadoDetalle.id( articuloManufacturadoDetalleDto.getId() );
        if ( articuloManufacturadoDetalleDto.getAlta() != null ) {
            articuloManufacturadoDetalle.alta( articuloManufacturadoDetalleDto.getAlta() );
        }
        articuloManufacturadoDetalle.cantidad( articuloManufacturadoDetalleDto.getCantidad() );
        articuloManufacturadoDetalle.articuloInsumo( iArticuloInsumoMapper.toEntity( articuloManufacturadoDetalleDto.getArticuloInsumo() ) );

        return articuloManufacturadoDetalle.build();
    }

    protected Set<ArticuloManufacturadoDetalle> articuloManufacturadoDetalleDtoSetToArticuloManufacturadoDetalleSet(Set<ArticuloManufacturadoDetalleDto> set) {
        if ( set == null ) {
            return null;
        }

        Set<ArticuloManufacturadoDetalle> set1 = new LinkedHashSet<ArticuloManufacturadoDetalle>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( ArticuloManufacturadoDetalleDto articuloManufacturadoDetalleDto : set ) {
            set1.add( articuloManufacturadoDetalleDtoToArticuloManufacturadoDetalle( articuloManufacturadoDetalleDto ) );
        }

        return set1;
    }
}
