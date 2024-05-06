package com.example.buensaborback.domain.dtos.empresa;
import com.example.buensaborback.domain.dtos.sucursal.SucursalFullDto;
import lombok.*;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    @Builder
    public class EmpresaFullDto {
        private String nombre;
        private String razonSocial;
        private Integer cuil;
        private SucursalFullDto sucursalFullDto;
    }


