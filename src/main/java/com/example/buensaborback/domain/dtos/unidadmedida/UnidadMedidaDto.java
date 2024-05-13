package com.example.buensaborback.domain.dtos.unidadmedida;

import com.example.buensaborback.domain.dtos.BaseDto;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UnidadMedidaDto extends BaseDto {

    String denominacion;

}
