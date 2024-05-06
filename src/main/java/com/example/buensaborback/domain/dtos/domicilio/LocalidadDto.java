package com.example.buensaborback.domain.dtos.domicilio;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class LocalidadDto {

     private String nombre;
     private ProvinciaDto provinciaDto;
}

