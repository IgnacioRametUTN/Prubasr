package com.example.buensaborback.domain.dtos.sucursal;

import com.example.buensaborback.domain.dtos.BaseDto;
import com.example.buensaborback.domain.dtos.empresa.EmpresaDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SucursalDto extends BaseDto {
    String nombre;
    String horarioApertura;
    String horarioCierre;
    @JsonBackReference
    EmpresaDto empresa;
}
