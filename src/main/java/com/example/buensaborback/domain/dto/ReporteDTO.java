package com.example.buensaborback.domain.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class ReporteDTO {
    private double totalIngresos;
    private double totalCostos;
    private double totalGanancias;
}
