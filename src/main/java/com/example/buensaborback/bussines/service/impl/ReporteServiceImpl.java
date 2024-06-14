package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.ReporteService;
import com.example.buensaborback.domain.dto.ReporteDTO;
import com.example.buensaborback.domain.entities.Articulo;
import com.example.buensaborback.domain.entities.Pedido;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReporteServiceImpl implements ReporteService {

    private PedidoServiceImpl pedidoService;

    public ReporteServiceImpl(PedidoServiceImpl pedidoService) {
        this.pedidoService = pedidoService;
    }

    public List<Object>FindTopProducts(LocalDate startDate, LocalDate endDate){
        return pedidoService.findTopProducts(startDate,endDate);

    }
    public ReporteDTO findPedidosBetweenDates(LocalDate startDate, LocalDate endDate) {
        List<Pedido> pedidos= pedidoService.findPedidosBetweenDates(startDate, endDate);
        ReporteDTO reporteDTO=new ReporteDTO(0,0,0);
        for (Pedido p:pedidos) {
            reporteDTO.setTotalIngresos(p.getTotal()+reporteDTO.getTotalIngresos());
            reporteDTO.setTotalCostos(p.getTotalCosto()+reporteDTO.getTotalCostos());
            reporteDTO.setTotalGanancias(p.getTotal()-p.getTotalCosto()+ reporteDTO.getTotalGanancias());
        }
        return reporteDTO;
    }
}
