package com.example.buensaborback.domain.dtos.sucursal;

import lombok.*;

import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SucursalHoraDto {
    private LocalTime horarioApertura;
    private LocalTime horarioCierre;
}
