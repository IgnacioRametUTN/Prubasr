package com.example.buensaborback.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
public class HistPrecio extends Base{
    Double cantidad;
    LocalDate fecha;
    @ManyToOne(cascade = CascadeType.ALL )
            @JoinColumn(name = "id_pedido")
    Pedido pedido;
}
