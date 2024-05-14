package com.example.buensaborback.domain.dtos.articulos.insumo;

import com.example.buensaborback.domain.dtos.articulos.ArticuloDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticuloInsumoDto extends ArticuloDto {
    Double precioCompra;
    Integer stockActual;
    Integer stockMaximo;
    Boolean esParaElaborar;

}
