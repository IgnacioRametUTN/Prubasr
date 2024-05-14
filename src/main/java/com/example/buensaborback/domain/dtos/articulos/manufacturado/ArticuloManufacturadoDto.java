package com.example.buensaborback.domain.dtos.articulos.manufacturado;

import com.example.buensaborback.domain.dtos.articulos.ArticuloDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticuloManufacturadoDto extends ArticuloDto {

     String descripcion;
     Integer tiempoEstimadoMinutos;
     String preparacion;
     Set<ArticuloManufacturadoDetalleDto> articuloManufacturadoDetalles ;

}
