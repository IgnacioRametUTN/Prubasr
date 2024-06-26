package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.IPedidoService;
import com.example.buensaborback.domain.entities.Factura;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.stream.Stream;

import com.example.buensaborback.domain.entities.DetallePedido;
import com.example.buensaborback.domain.entities.Pedido;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PDFGenerator {
    private final IPedidoService pedidoService;

    @Autowired
    public PDFGenerator(IPedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    public ByteArrayOutputStream generateFacturaPDF(Factura factura) throws DocumentException, IOException {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, out);

        document.open();
        document.add(new Paragraph("Factura #" + factura.getId()));
        document.add(new Paragraph("Fecha de Facturación: " + factura.getFechaFacturacion()));
        document.add(new Paragraph("Total Venta: " + factura.getTotalVenta()));
        document.add(new Paragraph("Forma de Pago: " + factura.getFormaPago()));

        // Tabla de Detalles del Pedido
        PdfPTable table = new PdfPTable(3);
        addTableHeader(table);
        Pedido pedido= pedidoService.findByFactura(factura);
        addRows(table, pedido);
        document.add(table);

        document.close();
        return out;
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Artículo", "Cantidad", "Subtotal").forEach(columnTitle -> {
            PdfPCell header = new PdfPCell();
            header.setPhrase(new Paragraph(columnTitle));
            table.addCell(header);
        });
    }

    private static void addRows(PdfPTable table, Pedido pedido) {
        for (DetallePedido detalle : pedido.getDetallePedidos()) {
            table.addCell(detalle.getArticulo().getDenominacion());
            table.addCell(detalle.getCantidad().toString());
            table.addCell(detalle.getSubTotal().toString());
        }
    }
}
