package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.bussines.service.IPedidoService;
import com.example.buensaborback.bussines.service.impl.PedidoServiceImpl;
import com.example.buensaborback.domain.entities.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final IPedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoServiceImpl pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("")
    public ResponseEntity<Long> save(@RequestBody Pedido pedido) {
        try {
            Pedido nuevoPedido = pedidoService.save(pedido);
            return ResponseEntity.ok(nuevoPedido.getId());
        } catch (Exception e) {
            System.err.println("Error al guardar el pedido: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(-1L); // Retorna -1 en caso de error
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Pedido>> getAll() {
        List<Pedido> pedidos = pedidoService.getAll();
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getOne(@PathVariable("id") Long id) {
        return new ResponseEntity<>(pedidoService.getPedidoById(id), HttpStatus.OK);

    }
    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<Pedido>> getAllByFecha(@PathVariable("fecha") LocalDate fecha) {
        List<Pedido> pedidos = pedidoService.getAllByFecha(fecha);
        return new ResponseEntity<>(pedidos, HttpStatus.OK);
    }

}

