package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.bussines.service.impl.FacturaServiceImpl;
import com.example.buensaborback.bussines.service.impl.PDFGenerator;
import com.example.buensaborback.domain.entities.Factura;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;

@RestController
public class FacturaController {

    @Autowired
    private FacturaServiceImpl facturaService;

    @GetMapping("/api/factura/{facturaId}")
    public ResponseEntity<byte[]> generatePdfFactura(@PathVariable Long facturaId) {
        try {
            Optional<Factura> optionalFactura = facturaService.findById(facturaId);
            if (optionalFactura.isPresent()) {
                Factura factura = optionalFactura.get();
                ByteArrayOutputStream pdfStream = PDFGenerator.generateFacturaPDF(factura);

                byte[] pdfBytes = pdfStream.toByteArray();

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_PDF);
                headers.setContentDispositionFormData("attachment", "factura_" + facturaId + ".pdf");
                headers.setContentLength(pdfBytes.length);

                return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IOException | DocumentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
