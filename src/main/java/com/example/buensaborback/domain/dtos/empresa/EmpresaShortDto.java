package com.example.buensaborback.domain.dtos.empresa;
import com.example.buensaborback.domain.dtos.sucursal.SucursalDto;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class EmpresaShortDto {
    private String nombre;
    private List<SucursalDto>listaSucursales;

}
