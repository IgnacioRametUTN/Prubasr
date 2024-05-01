package com.example.buensaborback.domain.dtos.cliente;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder


public class ClienteShortDto {
    private Integer id;
    private String nombre;
    private String apellido;
    private String telefono;
}
