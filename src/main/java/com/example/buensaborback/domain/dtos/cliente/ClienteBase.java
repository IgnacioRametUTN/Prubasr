package com.example.buensaborback.domain.dtos.cliente;
import com.example.buensaborback.domain.dtos.BaseDto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ClienteBase extends BaseDto {
    private String nombre;
    private String apellido;
    private String telefono;
}
