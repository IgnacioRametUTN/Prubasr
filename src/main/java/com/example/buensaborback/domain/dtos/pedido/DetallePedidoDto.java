package com.example.buensaborback.domain.dtos.pedido;

import com.example.buensaborback.domain.dtos.BaseDto;
import com.example.buensaborback.domain.dtos.articulos.ArticuloDto;
import com.example.buensaborback.domain.entities.Articulo;
import com.example.buensaborback.domain.entities.Base;
import com.example.buensaborback.domain.entities.Pedido;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class DetallePedidoDto extends BaseDto {
    private Integer cantidad;
    private Double subTotal;

    @ManyToOne(cascade = CascadeType.ALL)
    private ArticuloDto articulo;

}
