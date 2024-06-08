package com.example.buensaborback.domain.entities;

import com.example.buensaborback.domain.entities.enums.TipoPromocion;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@SuperBuilder
public class Promocion extends Base {

    private String denominacion;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
    private LocalTime horaDesde;
    private LocalTime horaHasta;
    private String descripcionDescuento;
    private Double precioPromocional;
    private TipoPromocion tipoPromocion;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "detallePedido_id")
    private DetallePedido detallePedido;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "promocion_id")
    @Builder.Default
    private Set<Imagen> imagenes= new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "promocion", fetch = FetchType.LAZY)
    @Builder.Default
    @JsonManagedReference
    private Set<PromocionDetalle> detallesPromocion = new HashSet<>();

}
