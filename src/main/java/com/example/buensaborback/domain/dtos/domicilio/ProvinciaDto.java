package com.example.buensaborback.domain.dtos.domicilio;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ProvinciaDto {
    private String nombre;
    private PaisDto paisDto;
}
