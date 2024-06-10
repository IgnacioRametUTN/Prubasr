package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.bussines.service.impl.MercadoPagoService;
import com.example.buensaborback.domain.entities.Pedido;
import com.example.buensaborback.domain.entities.PreferenceMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/mercado-pago")
public class MercadoPagoController {
    private final MercadoPagoService mercadoPagoService;

    @Autowired
    public MercadoPagoController(MercadoPagoService mercadoPagoService) {
        this.mercadoPagoService = mercadoPagoService;
    }

    @PostMapping("/crear_preference_mp")
    public PreferenceMP crearPreferenceMP(@RequestBody Pedido pedido) {
        try {
            PreferenceMP preferenceMP = mercadoPagoService.getPreferenciaIdMercadoPago(pedido);

            return preferenceMP;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}