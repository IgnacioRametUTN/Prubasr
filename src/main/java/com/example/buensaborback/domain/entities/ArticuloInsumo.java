package com.example.buensaborback.domain.entities;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@SuperBuilder
public class ArticuloInsumo extends Articulo {

    private Double precioCompra;
    private Double stockActual;
    private Double stockMaximo;
    private Boolean esParaElaborar;


}
