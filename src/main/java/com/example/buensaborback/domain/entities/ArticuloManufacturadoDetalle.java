package com.example.buensaborback.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@SuperBuilder
public class ArticuloManufacturadoDetalle extends Base {
    private Double cantidad;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "articulo_manufacturado_id")
    @ToString.Exclude
    private ArticuloManufacturado articuloManufacturado;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "articulo_insumo_id")
    private ArticuloInsumo articuloInsumo;


}
