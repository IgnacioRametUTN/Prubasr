package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.ReporteService;
import com.example.buensaborback.domain.dto.ReporteDTO;
import com.example.buensaborback.domain.entities.*;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
    public List<Object[]> findMovimientosBetween(LocalDate startDate, LocalDate endDate) {
        List<Pedido> pedidos= pedidoService.findPedidosBetweenDates(startDate, endDate);
        List<Object[]> lista = new ArrayList<>();
        String[] string = {"Fecha", "Ingresos", "Costos", "Ganancias"};
        lista.add(string);
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


    public ByteArrayInputStream generateExcelMovimientos(LocalDate startDate, LocalDate endDate){
        try {
            return generateExcelReport(startDate, endDate);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new NotFoundException("Hubo un Error!");
        }
    }

    private  ByteArrayInputStream generateExcelReport(LocalDate startDate, LocalDate endDate) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Movimientos");
            createHeaderRow(sheet, List.of("Fecha", "Ingresos", "Gastos", "Ganancias"));
            int rowNum = 1; //Avanzamos a la segunda fila porque la primera ya tiene el header
            List<Object[]> data = this.findMovimientosBetween(startDate, endDate);
            createPedidoRows(data,sheet, rowNum);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        }
    }

    private void createHeaderRow(Sheet sheet, List<String> headers) {
        Row headerRow = sheet.createRow(0);
        for (int column = 0; column < headers.size(); column++) {
            headerRow.createCell(column).setCellValue(headers.get(column));
        }

    }


    private void createPedidoRows(List<Object[]> data, Sheet sheet, int rowIndex) {
        int row2 = rowIndex;
        Double total = 0.0;
        for (Object[] rowData : data) {
            Row row = sheet.createRow(row2);
            row.createCell(0).setCellValue(String.valueOf(rowData[0]));
            row.createCell(1).setCellValue(String.valueOf(rowData[1]));
            row.createCell(2).setCellValue(String.valueOf(rowData[2]));
            row.createCell(0).setCellValue(String.valueOf(rowData[3]));
            row2++;
        }



    }
}
