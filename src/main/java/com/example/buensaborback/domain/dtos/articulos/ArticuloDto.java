package com.example.buensaborback.domain.dtos.articulos;

import com.example.buensaborback.domain.dtos.BaseDto;
import com.example.buensaborback.domain.dtos.categoria.CategoriaDto;
import com.example.buensaborback.domain.dtos.promocion.PromocionDetalleDto;
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
     //Set<Imagen> imagenes;
     /*
     @JsonProperty("unidad_medida")
     UnidadMedida unidadMedida;
     Revisar Tmb los dto de pais provincia localidad
     hice dto solo de domicilio full short
     */
     //TODO: Hacer cambios en el main y los mappers
     CategoriaDto unidadMedida;
     CategoriaDto categoria;

     Set<PromocionDetalleDto> promocionDetalle;
}
