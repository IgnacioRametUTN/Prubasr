package com.example.buensaborback.domain.dtos.categoria;

import com.example.buensaborback.domain.dtos.BaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;
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
public class CategoriaDto extends BaseDto {
    String denominacion;
}
