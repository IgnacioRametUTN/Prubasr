package com.example.buensaborback.domain.dtos.pedido;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class DetallePedidoFullDto {
    private Integer cantidad;
    private Double subTotal;

}
