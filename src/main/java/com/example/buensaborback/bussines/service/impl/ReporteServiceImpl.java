package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.ReporteService;
import com.example.buensaborback.domain.dto.ReporteDTO;
import com.example.buensaborback.domain.entities.*;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
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


    public ByteArrayInputStream generateExcelMovimientos(LocalDate startDate, LocalDate endDate) {
        try {
            return generateExcelReport(startDate, endDate);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw new NotFoundException("Hubo un Error!");
        }
    }

    @Override
    public ByteArrayInputStream generateExcelPedidosBetween(LocalDate startDate, LocalDate endDate) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            Sheet sheet = workbook.createSheet("Pedidos");
            createHeaderRow(sheet, List.of("Id Pedido", "Fecha Pedido","Cliente", "Domicilio", "Forma Pago","Estado" , "Detalle", "Precio Venta", "Precio Costo", "Cantidad" , "Total Costo", "Total Ingreso", "Total Ganancia"));
            int rowNum = 1; // Avanzamos a la segunda fila porque la primera ya tiene el header
            List<Pedido> data = this.pedidoService.findPedidosBetweenDates(startDate, endDate);

            for (Pedido pedido : data){
                Row mainRow = sheet.createRow(rowNum);
                mainRow.createCell(0).setCellValue(pedido.getId());
                mainRow.createCell(1).setCellValue(pedido.getFechaPedido().toString());
                mainRow.createCell(2).setCellValue(pedido.getCliente().getNombre() + " " + pedido.getCliente().getApellido());
                String domicilioTemplate = "%s, %d, de %s, %s, Argentina.";
                mainRow.createCell(3).setCellValue(String.format(domicilioTemplate,
                        pedido.getDomicilio().getCalle(),
                        pedido.getDomicilio().getNumero(),
                        pedido.getDomicilio().getLocalidad().getNombre(),
                        pedido.getDomicilio().getLocalidad().getProvincia().getNombre()
                ));
                mainRow.createCell(4).setCellValue(pedido.getFormaPago().toString());
                mainRow.createCell(5).setCellValue(pedido.getEstado().toString());
                int contadorDetalles = rowNum ;
                for (DetallePedido detalle : pedido.getDetallePedidos()){
                    if(contadorDetalles == rowNum){
                        mainRow.createCell(6).setCellValue(detalle.getArticulo().getDenominacion());
                        mainRow.createCell(7).setCellValue(detalle.getArticulo().getPrecioVenta());
                        mainRow.createCell(8).setCellValue(detalle.getArticulo() instanceof ArticuloInsumo ? ((ArticuloInsumo) detalle.getArticulo()).getPrecioCompra() : 0);
                        mainRow.createCell(9).setCellValue(detalle.getCantidad());
                    }else{
                        Row detalleRow = sheet.createRow(contadorDetalles);
                        detalleRow.createCell(6).setCellValue(detalle.getArticulo().getDenominacion());
                        detalleRow.createCell(7).setCellValue(detalle.getArticulo().getPrecioVenta());
                        detalleRow.createCell(8).setCellValue(detalle.getArticulo() instanceof ArticuloInsumo ? ((ArticuloInsumo) detalle.getArticulo()).getPrecioCompra() : 0);
                        detalleRow.createCell(9).setCellValue(detalle.getCantidad());

                    }
                    contadorDetalles++;
                }
                mainRow.createCell(10).setCellValue(pedido.getTotalCosto());
                mainRow.createCell(11).setCellValue(pedido.getTotal());
                mainRow.createCell(12).setCellValue(pedido.getTotal() - pedido.getTotalCosto());

                //Combinar Celdas
                CellStyle style = workbook.createCellStyle();
                style.setAlignment(HorizontalAlignment.CENTER);
                style.setVerticalAlignment(VerticalAlignment.CENTER);
                //Columna del ID
                sheet.getRow(rowNum).getCell(0).setCellStyle(style);
                sheet.addMergedRegion(new CellRangeAddress(rowNum, contadorDetalles, 0, 0));
                //Columna de la Fecha
                sheet.getRow(rowNum).getCell(1).setCellStyle(style);
                sheet.addMergedRegion(new CellRangeAddress(rowNum, contadorDetalles, 1, 1));
                //Columna del Cliente
                sheet.getRow(rowNum).getCell(2).setCellStyle(style);
                sheet.addMergedRegion(new CellRangeAddress(rowNum, contadorDetalles, 2, 2));
                //Columna del Domicilio
                sheet.getRow(rowNum).getCell(3).setCellStyle(style);
                sheet.addMergedRegion(new CellRangeAddress(rowNum, contadorDetalles, 3, 3));
                //Columna de la Forma de Pago
                sheet.getRow(rowNum).getCell(4).setCellStyle(style);
                sheet.addMergedRegion(new CellRangeAddress(rowNum, contadorDetalles, 4, 4));
                //Columna del Estado
                sheet.getRow(rowNum).getCell(5).setCellStyle(style);
                sheet.addMergedRegion(new CellRangeAddress(rowNum, contadorDetalles, 5, 5));
                //Columna del Total Costo
                sheet.getRow(rowNum).getCell(10).setCellStyle(style);
                sheet.addMergedRegion(new CellRangeAddress(rowNum, contadorDetalles, 10, 10));
                //Columna del Total Ingreso
                sheet.getRow(rowNum).getCell(11).setCellStyle(style);
                sheet.addMergedRegion(new CellRangeAddress(rowNum, contadorDetalles, 11, 11));
                //Columna del Total Ganancia
                sheet.getRow(rowNum).getCell(12).setCellStyle(style);
                sheet.addMergedRegion(new CellRangeAddress(rowNum, contadorDetalles, 12, 12));
                rowNum += pedido.getDetallePedidos().size() +1;
            }
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } finally {
            workbook.close();
            outputStream.close();
        }
    }

    private ByteArrayInputStream generateExcelReport(LocalDate startDate, LocalDate endDate) throws IOException {
        Workbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            Sheet sheet = workbook.createSheet("Movimientos");
            createHeaderRow(sheet, List.of("Fecha", "Ingresos", "Gastos", "Ganancias"));
            int rowNum = 1; // Avanzamos a la segunda fila porque la primera ya tiene el header
            List<Object[]> data = this.findMovimientosBetween(startDate, endDate);
            createPedidoRows(data, sheet, rowNum);

            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());
        } finally {
            workbook.close();
            outputStream.close();
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
        for (Object[] rowData : data) {
            Row row = sheet.createRow(row2);
            row.createCell(0).setCellValue(String.valueOf(rowData[0]));
            row.createCell(1).setCellValue(String.valueOf(rowData[1]));
            row.createCell(2).setCellValue(String.valueOf(rowData[2]));
            row.createCell(3).setCellValue(String.valueOf(rowData[3]));
            row2++;
        }
    }
}
