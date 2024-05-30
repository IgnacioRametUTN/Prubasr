package com.example.buensaborback.bussines.mappers;

import com.example.buensaborback.domain.dtos.ImagenDto;
import com.example.buensaborback.domain.dtos.articulos.insumo.ArticuloInsumoDto;
import com.example.buensaborback.domain.dtos.categoria.CategoriaDto;
import com.example.buensaborback.domain.dtos.promocion.PromocionDetalleDto;
import com.example.buensaborback.domain.dtos.unidadmedida.UnidadMedidaDto;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
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
public class IArticuloInsumoMapperImpl implements IArticuloInsumoMapper {

    @Autowired
    private IPromocionDetalleMapper iPromocionDetalleMapper;

    @Override
    public ArticuloInsumoDto toDTO(ArticuloInsumo source) {
        if ( source == null ) {
            return null;
        }

        ArticuloInsumoDto.ArticuloInsumoDtoBuilder<?, ?> articuloInsumoDto = ArticuloInsumoDto.builder();

        articuloInsumoDto.id( source.getId() );
        articuloInsumoDto.alta( source.isAlta() );
        articuloInsumoDto.denominacion( source.getDenominacion() );
        articuloInsumoDto.precioVenta( source.getPrecioVenta() );
        articuloInsumoDto.imagenes( imagenSetToImagenDtoSet( source.getImagenes() ) );
        articuloInsumoDto.unidadMedida( unidadMedidaToUnidadMedidaDto( source.getUnidadMedida() ) );
        articuloInsumoDto.categoria( categoriaToCategoriaDto( source.getCategoria() ) );
        articuloInsumoDto.promocionDetalle( promocionDetalleSetToPromocionDetalleDtoSet( source.getPromocionDetalle() ) );
        articuloInsumoDto.precioCompra( source.getPrecioCompra() );
        articuloInsumoDto.stockActual( source.getStockActual() );
        articuloInsumoDto.stockMaximo( source.getStockMaximo() );
        articuloInsumoDto.esParaElaborar( source.getEsParaElaborar() );

        return articuloInsumoDto.build();
    }

    @Override
    public ArticuloInsumo toEntity(ArticuloInsumoDto source) {
        if ( source == null ) {
            return null;
        }

        ArticuloInsumo.ArticuloInsumoBuilder<?, ?> articuloInsumo = ArticuloInsumo.builder();

        articuloInsumo.id( source.getId() );
        if ( source.getAlta() != null ) {
            articuloInsumo.alta( source.getAlta() );
        }
        articuloInsumo.denominacion( source.getDenominacion() );
        articuloInsumo.precioVenta( source.getPrecioVenta() );
        articuloInsumo.imagenes( imagenDtoSetToImagenSet( source.getImagenes() ) );
        articuloInsumo.unidadMedida( unidadMedidaDtoToUnidadMedida( source.getUnidadMedida() ) );
        articuloInsumo.categoria( categoriaDtoToCategoria( source.getCategoria() ) );
        articuloInsumo.promocionDetalle( promocionDetalleDtoSetToPromocionDetalleSet( source.getPromocionDetalle() ) );
        articuloInsumo.precioCompra( source.getPrecioCompra() );
        articuloInsumo.stockActual( source.getStockActual() );
        articuloInsumo.stockMaximo( source.getStockMaximo() );
        articuloInsumo.esParaElaborar( source.getEsParaElaborar() );

        return articuloInsumo.build();
    }

    @Override
    public List<ArticuloInsumoDto> toDTOsList(List<ArticuloInsumo> source) {
        if ( source == null ) {
            return null;
        }

        List<ArticuloInsumoDto> list = new ArrayList<ArticuloInsumoDto>( source.size() );
        for ( ArticuloInsumo articuloInsumo : source ) {
            list.add( toDTO( articuloInsumo ) );
        }

        return list;
    }

    @Override
    public List<ArticuloInsumo> toEntitiesList(List<ArticuloInsumoDto> source) {
        if ( source == null ) {
            return null;
        }

        List<ArticuloInsumo> list = new ArrayList<ArticuloInsumo>( source.size() );
        for ( ArticuloInsumoDto articuloInsumoDto : source ) {
            list.add( toEntity( articuloInsumoDto ) );
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
}
