package com.example.buensaborback.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@SuperBuilder

public class ArticuloManufacturado extends Articulo{

    private String descripcion;
    private Integer tiempoEstimadoMinutos;
    private String preparacion;

    @OneToMany(mappedBy = "articuloManufacturado", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default

    @JsonIgnoreProperties({"hibernateLazyInitializer", "articuloManufacturado"})
    private Set<ArticuloManufacturadoDetalle> articuloManufacturadoDetalles = new HashSet<>();
}
