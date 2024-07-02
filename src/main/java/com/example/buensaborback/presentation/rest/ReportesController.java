package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.bussines.service.ReporteService;
import com.example.buensaborback.domain.dto.ReporteDTO;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
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
        return reporteService.findTopProducts(startDate, endDate);
    }

    @GetMapping("/reporte-diario")
    public List<Object[]> findMovimientosMonetariosBetween(@RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate) {
        return reporteService.findMovimientosBetween(startDate, endDate);
    }
    @GetMapping("/reporte-diario/excel")
    public ResponseEntity<?> generateExcelMovimientosMonetariosBetween(@RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate) {
        try {
            ByteArrayInputStream excelStream = reporteService.generateExcelMovimientos(startDate, endDate);
            byte[] excelBytes = IOUtils.toByteArray(excelStream);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            headers.setContentDispositionFormData("attachment", "reporte_pedidos.xlsx");
            headers.setContentLength(excelBytes.length);
            return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/pedidos/excel")
    public ResponseEntity<?> generateExcelPedidosBetween(@RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate) {
        try {
            ByteArrayInputStream excelStream = reporteService.generateExcelPedidosBetween(startDate, endDate);
            byte[] excelBytes = IOUtils.toByteArray(excelStream);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
            headers.setContentDispositionFormData("attachment", "reporte_pedidos.xlsx");
            headers.setContentLength(excelBytes.length);
            return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/reporte-totales")
    public ReporteDTO findPedidosBetweenDates(@RequestParam("startDate") LocalDate startDate, @RequestParam("endDate") LocalDate endDate) {
        return reporteService.findPedidosBetweenDates(startDate, endDate);
    }
}
