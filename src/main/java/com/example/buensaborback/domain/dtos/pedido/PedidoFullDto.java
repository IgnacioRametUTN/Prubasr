package com.example.buensaborback.domain.dtos.pedido;

import com.example.buensaborback.domain.dtos.cliente.ClienteShortDto;
import com.example.buensaborback.domain.dtos.domicilio.DomicilioShortDto;
import com.example.buensaborback.domain.entities.DetallePedido;
import com.example.buensaborback.domain.entities.enums.Estado;
import com.example.buensaborback.domain.entities.enums.FormaPago;
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
public class PedidoFullDto {
    //TODO:acordarse forma de pago es enum config en mapper
    private LocalTime horaEstimadaFinalizacion;
    private Double total;
    private Double totalCosto;
    private Estado estado;
    private TipoEnvio tipoEnvio;
    private String formaDePago;
    private LocalDate fechaPedido;
    private DomicilioShortDto domicilioShortDto;
    private ClienteShortDto clienteShortDto;
    private List<DetallePedido> DetallePedidoDtoList;

}
