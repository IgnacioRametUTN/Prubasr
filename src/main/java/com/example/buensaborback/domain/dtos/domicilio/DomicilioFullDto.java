package com.example.buensaborback.domain.dtos.domicilio;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class DomicilioFullDto {
    private String calle;
    private Integer numero;
    private Integer cp;
    private String localidad;
    private String provincia;
    private String pais;
}
