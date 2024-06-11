package com.example.buensaborback.bussines.service;

import com.example.buensaborback.domain.dto.ReporteDTO;
import com.example.buensaborback.domain.entities.Articulo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface ReporteService {
    List<Object> FindTopProducts(LocalDate startDate, LocalDate endDate);
    ReporteDTO findPedidosBetweenDates(LocalDate startDate, LocalDate endDate);
}
