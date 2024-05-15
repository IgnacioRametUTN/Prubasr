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

    @ManyToOne
    private Articulo articulo;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;


    @OneToMany(cascade=CascadeType.ALL,mappedBy = "detallePedido")
    private Set<Promocion>promociones=new HashSet<>();

}
