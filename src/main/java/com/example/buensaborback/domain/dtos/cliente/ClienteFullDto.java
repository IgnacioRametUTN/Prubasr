package com.example.buensaborback.domain.dtos.cliente;

import com.example.buensaborback.domain.dtos.BaseDto;
import com.example.buensaborback.domain.dtos.domicilio.DomicilioFullDto;
import com.example.buensaborback.domain.dtos.domicilio.DomicilioShortDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class ClienteFullDto extends BaseDto {
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    @JsonProperty("fecha_nacimiento")
    private LocalDate fechaNacimiento;
    @JsonProperty("url_imagen")
    private String imagen;
    private List<DomicilioFullDto> domicilios;
}
