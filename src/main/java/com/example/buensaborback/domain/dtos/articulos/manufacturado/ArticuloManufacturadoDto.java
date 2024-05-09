package com.example.buensaborback.domain.dtos.articulos.manufacturado;

import com.example.buensaborback.domain.dtos.BaseDto;
import com.example.buensaborback.domain.entities.Categoria;
import com.example.buensaborback.domain.entities.Imagen;
import com.example.buensaborback.domain.entities.Promocion;
import com.example.buensaborback.domain.entities.UnidadMedida;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor

@Getter
@Setter
@ToString
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticuloManufacturadoDto extends BaseDto {

    String denominacion;
    @JsonProperty("precio_venta")
    Double precioVenta;
 //   Set<String> imagenes;
    @JsonProperty("unidad_medida")
    String unidadMedida;
    Set<Promocion> promociones;
    Categoria categoria;
}
