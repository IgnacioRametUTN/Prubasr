package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.bussines.service.impl.MercadoPagoService;
import com.example.buensaborback.domain.entities.Pedido;
import com.example.buensaborback.domain.entities.PreferenceMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/MercadoPago")
public class MercadoPagoController {
    @Autowired
    private MercadoPagoService mercadoPagoService;


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