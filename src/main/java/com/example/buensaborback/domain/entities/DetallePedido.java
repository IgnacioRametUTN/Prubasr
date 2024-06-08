package com.example.buensaborback.domain.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@SuperBuilder
public class DetallePedido extends Base{

    private Integer cantidad;
    private Double subTotal;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Articulo articulo;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;


    @OneToMany(cascade=CascadeType.ALL,mappedBy = "detallePedido", fetch = FetchType.LAZY)
    @Builder.Default
    private Set<Promocion>promociones = new HashSet<>();

}
