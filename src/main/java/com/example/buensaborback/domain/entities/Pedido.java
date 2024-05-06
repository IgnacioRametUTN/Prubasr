package com.example.buensaborback.domain.entities;

import com.example.buensaborback.domain.entities.enums.Estado;
import com.example.buensaborback.domain.entities.enums.FormaPago;
import com.example.buensaborback.domain.entities.enums.TipoEnvio;
import jakarta.persistence.*;
import lombok.*;
import com.example.buensaborback.domain.entities.Empleado;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@SuperBuilder
public class Pedido extends Base{

    private LocalTime horaEstimadaFinalizacion;
    private Double total;
    private Double totalCosto;
    private Estado estado;
    private TipoEnvio tipoEnvio;
    private FormaPago formaPago;
    private LocalDate fechaPedido;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    @ManyToOne(cascade = CascadeType.ALL) //Dirección a donde se envía el pedido
    private Domicilio domicilio;

    @ManyToOne(cascade = CascadeType.ALL)
    private Sucursal sucursal;

    @OneToOne(cascade = CascadeType.ALL)
    private Factura factura;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    @Builder.Default
    private Set<DetallePedido> detallePedidos = new HashSet<>();


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    @ToString.Exclude
    private Cliente cliente;


}
