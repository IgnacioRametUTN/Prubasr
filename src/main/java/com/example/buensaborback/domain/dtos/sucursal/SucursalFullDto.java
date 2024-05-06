package com.example.buensaborback.domain.dtos.sucursal;

import com.example.buensaborback.domain.dtos.domicilio.DomicilioFullDto;
import lombok.*;

import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SucursalFullDto {
    private String nombre;
    private LocalTime horarioApertura;
    private LocalTime horarioCierre;
    private DomicilioFullDto domicilioFullDto;


}
