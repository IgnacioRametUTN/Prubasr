package com.example.buensaborback.domain.dtos.empresa;
import com.example.buensaborback.domain.dtos.BaseDto;
import com.example.buensaborback.domain.dtos.sucursal.SucursalDto;
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
public class EmpresaDto extends BaseDto {
    String nombre;
    String razonSocial;
    Long cuil;

    SucursalDto sucursal;

}



