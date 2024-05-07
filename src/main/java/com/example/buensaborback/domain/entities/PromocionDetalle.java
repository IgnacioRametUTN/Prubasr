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
public class PromocionDetalle extends Base{
   private Integer cantidad;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "promocion_id")
    @Builder.Default
    private Set<Articulo> articulos = new HashSet<>();


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "promocion_id")
    private Promocion promocion;
}
