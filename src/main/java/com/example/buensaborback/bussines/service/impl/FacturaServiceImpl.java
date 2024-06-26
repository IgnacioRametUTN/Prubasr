package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.IFacturaService;
import com.example.buensaborback.domain.entities.Factura;
import com.example.buensaborback.domain.entities.Pedido;
import com.example.buensaborback.repositories.FacturaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FacturaServiceImpl  implements IFacturaService {

    private final FacturaRepository facturaRepository;

    public FacturaServiceImpl(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    @Override
    public Factura crearFactura(Pedido pedido) {
        Factura factura = Factura.builder().build();
        factura.setFechaFacturacion(pedido.getFechaPedido());
        factura.setFormaPago(pedido.getFormaPago());
        factura.setTotalVenta(pedido.getTotal());
        return this.facturaRepository.save(factura);
        //mandarCorreoFactura(pedido.getCliente().getEmail(), factura);

    }
    public Optional<Factura> findById(Long facturaId) {
        return facturaRepository.findById(facturaId);
    }
}
