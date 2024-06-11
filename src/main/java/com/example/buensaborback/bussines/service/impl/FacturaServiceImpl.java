package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.IFacturaService;
import com.example.buensaborback.domain.entities.Factura;
import com.example.buensaborback.domain.entities.Pedido;
import com.example.buensaborback.repositories.FacturaRepository;
import org.springframework.stereotype.Service;

@Service
public class FacturaServiceImpl  implements IFacturaService {

    private final FacturaRepository facturaRepository;

    public FacturaServiceImpl(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    @Override
    public void crearFactura(Pedido pedido) {
        Factura factura = Factura.builder().build();
        factura.setFechaFacturacion(pedido.getFechaPedido());
        factura.setTotalVenta(pedido.getTotal());
        this.facturaRepository.save(factura);
        //mandarCorreoFactura(pedido.getCliente().getEmail(), factura);

    }
}
