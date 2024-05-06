package com.example.buensaborback.domain.dtos.domicilio;

import com.example.buensaborback.domain.dtos.BaseDto;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
public class DomicilioFullDto extends BaseDto {
    private String calle;
    private Integer numero;
    private Integer cp;
    private String localidad;
    private String provincia;
    private String pais;
}
