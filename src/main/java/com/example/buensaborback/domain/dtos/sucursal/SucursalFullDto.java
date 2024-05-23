package com.example.buensaborback.domain.dtos.sucursal;

import com.example.buensaborback.domain.dtos.BaseDto;
import com.example.buensaborback.domain.dtos.domicilio.DomicilioFullDto;
import lombok.*;

import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class SucursalFullDto  {
    private Long id;
    private String nombre;
    private String horarioApertura;
    private String horarioCierre;
    private Long idEmpresa;


}
