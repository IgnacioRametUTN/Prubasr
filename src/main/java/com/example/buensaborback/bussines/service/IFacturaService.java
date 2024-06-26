package com.example.buensaborback.bussines.service;

import com.example.buensaborback.domain.entities.Factura;
import com.example.buensaborback.domain.entities.Pedido;

import java.util.Optional;

public interface IFacturaService {
   Factura crearFactura(Pedido pedido);
   Optional<Factura> findById(Long facturaId);
   void enviarFacturaPorEmail(String email, Factura factura);
}
