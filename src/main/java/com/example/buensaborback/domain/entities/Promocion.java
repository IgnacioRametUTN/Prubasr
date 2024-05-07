package com.example.buensaborback.domain.entities;

import com.example.buensaborback.domain.entities.enums.TipoPromocion;
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


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "promocion_articulo",
            joinColumns = @JoinColumn(name = "promocion_id"),
            inverseJoinColumns = @JoinColumn(name = "articulo_id"))
    @Builder.Default
    //TODO: revisar si articulo tiene que estar aca y en DetallePromocion
    private Set<Articulo> articulos= new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "promocion_id")
    @Builder.Default
    private Set<Imagen> imagenes= new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "promocion")
    @Builder.Default
    private Set<PromocionDetalle> detallesPromocion = new HashSet<>();

}
