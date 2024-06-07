package com.example.buensaborback.domain.dtos.pedido;

import com.example.buensaborback.domain.dtos.cliente.ClienteFullDto;
import com.example.buensaborback.domain.dtos.domicilio.domicilioShort;
import com.example.buensaborback.domain.entities.enums.Estado;
import com.example.buensaborback.domain.entities.enums.TipoEnvio;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PedidoShortDto {
    //TODO:acordarse forma de pago es enum config en mapper
    private Long id;
    private LocalTime horaEstimadaFinalizacion;
    private Double total;
    private Estado estado;
    private LocalDate fechaPedido;
    private TipoEnvio tipoEnvio;
    private String formaDePago;
    private domicilioShort domicilioShort;
    private ClienteFullDto cliente;
    private List<DetallePedidoDto> detallePedidos;

}
