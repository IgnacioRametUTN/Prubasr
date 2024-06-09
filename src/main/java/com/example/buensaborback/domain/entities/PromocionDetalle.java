package com.example.buensaborback.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class PromocionDetalle extends Base{
   private Integer cantidad;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "articulo_id")
    @JsonManagedReference
    private Articulo articulo;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "promocion_id")
    @JsonBackReference
    private Promocion promocion;
}
