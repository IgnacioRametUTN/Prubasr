package com.example.buensaborback.domain.dtos.domicilio;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class domicilioShort {
    private String calle;
    private Integer numero;
    private String localidad;
}
