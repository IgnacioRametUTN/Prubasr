package com.example.buensaborback.domain.dtos.promocion;

import com.example.buensaborback.domain.dtos.BaseDto;
import com.example.buensaborback.domain.entities.DetallePedido;
import com.example.buensaborback.domain.entities.Imagen;
import com.example.buensaborback.domain.entities.PromocionDetalle;
import com.example.buensaborback.domain.entities.enums.TipoPromocion;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PromocionDto extends BaseDto{

    String denominacion;
    LocalDate fechaDesde;
    LocalDate fechaHasta;
    LocalTime horaDesde;
    LocalTime horaHasta;
    String descripcionDescuento;
    Double precioPromocional;
    TipoPromocion tipoPromocion;

    //DetallePedido detallePedido;
    //Set<Imagen> imagenes= new HashSet<>();
    Set<PromocionDetalleDto> detallesPromocion;
}
