package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.ReporteService;
import com.example.buensaborback.domain.dto.ReporteDTO;
import com.example.buensaborback.domain.entities.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ReporteServiceImpl implements ReporteService {

    private PedidoServiceImpl pedidoService;

    public ReporteServiceImpl(PedidoServiceImpl pedidoService) {
        this.pedidoService = pedidoService;
    }

    public List<Object> findTopProducts(LocalDate startDate, LocalDate endDate){
        List<Object> ranking = new ArrayList<>();
        ranking.add(Arrays.asList("Comidas", "Ventas"));
        ranking.addAll(pedidoService.findTopProducts(startDate,endDate));
        return ranking;
    }

    @Override
    public List<Object> findMovimientosBetween(LocalDate startDate, LocalDate endDate) {
        List<Pedido> pedidos= pedidoService.findPedidosBetweenDates(startDate, endDate);
        List<Object> lista = new ArrayList<>();
        lista.add(Arrays.asList("Fecha", "Ingresos", "Costos", "Ganancias"));
        for (Pedido pedido : pedidos){
            Object[] row = new Object[4];
            row[0] = pedido.getFechaPedido().toString();
            double ingreso = 0.0;
            double costo = 0.0;
            for (DetallePedido detallePedido : pedido.getDetallePedidos()){
                Articulo articulo = detallePedido.getArticulo();
                ingreso += articulo.getPrecioVenta() * detallePedido.getCantidad();
                if(articulo instanceof ArticuloInsumo){
                    costo += ((ArticuloInsumo) articulo).getPrecioCompra() * detallePedido.getCantidad();
                }
            }
            row[1]= ingreso;
            row[2]= costo;
            row[3] = ingreso - costo;
            lista.add(row);
        }
        return lista;
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
