package com.example.buensaborback.domain.dtos.articulos.manufacturado;

import com.example.buensaborback.domain.dtos.BaseDto;
import com.example.buensaborback.domain.entities.Articulo;
import com.example.buensaborback.domain.entities.Imagen;
import com.example.buensaborback.domain.entities.PromocionDetalle;
import com.example.buensaborback.domain.entities.enums.TipoPromocion;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PromocionDto extends BaseDto {

     String denominacion;
     LocalDate fechaDesde;
     LocalDate fechaHasta;
     LocalTime horaDesde;
     LocalTime horaHasta;
     String descripcionDescuento;
     Double precioPromocional;
     TipoPromocion tipoPromocion;
     Set<Articulo> articulos;
     Set<Imagen> imagenes;
     Set<PromocionDetalle> detallesPromocion ;
}
