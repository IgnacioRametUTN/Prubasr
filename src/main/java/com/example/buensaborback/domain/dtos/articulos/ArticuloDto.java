package com.example.buensaborback.domain.dtos.articulos;

import com.example.buensaborback.domain.dtos.BaseDto;
import com.example.buensaborback.domain.dtos.ImagenDto;
import com.example.buensaborback.domain.dtos.categoria.CategoriaDto;
import com.example.buensaborback.domain.dtos.promocion.PromocionDetalleDto;
import com.example.buensaborback.domain.dtos.unidadmedida.UnidadMedidaDto;
import com.example.buensaborback.domain.entities.Imagen;
import com.example.buensaborback.domain.entities.UnidadMedida;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import java.util.Set;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
public class ArticuloDto extends BaseDto {

     String denominacion;
     Double precioVenta;
     Set<ImagenDto> imagenes;
     UnidadMedidaDto unidadMedida;
     CategoriaDto categoria;
     Set<PromocionDetalleDto> promocionDetalle;
}
