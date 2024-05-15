package com.example.buensaborback.domain.dtos.promocion;

import com.example.buensaborback.domain.dtos.BaseDto;
import com.example.buensaborback.domain.dtos.articulos.ArticuloDto;
import com.example.buensaborback.domain.entities.Articulo;
import com.example.buensaborback.domain.entities.Promocion;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PromocionDetalleDto extends BaseDto {

    Integer cantidad;
}
