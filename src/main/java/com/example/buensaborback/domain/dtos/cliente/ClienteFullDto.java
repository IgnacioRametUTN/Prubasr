package com.example.buensaborback.domain.dtos.cliente;

import com.example.buensaborback.domain.dtos.domicilio.DomicilioShortDto;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class ClienteFullDto {
    //TODO:Agregar imagen
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private LocalDate fechaNacimiento;
    private DomicilioShortDto domicilioShortDto;
}
