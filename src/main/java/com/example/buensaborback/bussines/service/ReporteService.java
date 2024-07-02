package com.example.buensaborback.bussines.service;

import com.example.buensaborback.domain.dto.ReporteDTO;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public interface ReporteService {
    List<Object> findTopProducts(LocalDate startDate, LocalDate endDate);
    List<Object[]> findMovimientosBetween(LocalDate startDate, LocalDate endDate);
    ReporteDTO findPedidosBetweenDates(LocalDate startDate, LocalDate endDate);

    ByteArrayInputStream generateExcelMovimientos(LocalDate startDate, LocalDate endDate);

    ByteArrayInputStream generateExcelPedidosBetween(LocalDate startDate, LocalDate endDate) throws IOException;
}
