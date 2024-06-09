package com.example.buensaborback.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

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
    @JsonBackReference(value = "manufacturado-detalle")
    private ArticuloManufacturado articuloManufacturado;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "articulo_insumo_id")
    @JsonIgnore
    private ArticuloInsumo articuloInsumo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticuloManufacturadoDetalle that = (ArticuloManufacturadoDetalle) o;
        return Objects.equals(cantidad, that.cantidad) && Objects.equals(articuloManufacturado, that.articuloManufacturado) && Objects.equals(articuloInsumo, that.articuloInsumo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cantidad, articuloManufacturado, articuloInsumo);
    }
}
