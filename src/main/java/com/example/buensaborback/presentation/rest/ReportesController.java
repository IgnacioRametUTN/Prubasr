package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.bussines.service.ReporteService;
import com.example.buensaborback.domain.dto.ReporteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reportes")
@CrossOrigin("*")
public class ReportesController {
    private final ReporteService reporteService;
    @Autowired
    public ReportesController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }


    @GetMapping("/top-products")
    public List<Object> findTopProducts(@RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate) {
        return reporteService.FindTopProducts(startDate, endDate);
    }

    @GetMapping("/ReporteTotales")
    public ReporteDTO findPedidosBetweenDates(@RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate) {
        return reporteService.findPedidosBetweenDates(startDate, endDate);
    }
}
