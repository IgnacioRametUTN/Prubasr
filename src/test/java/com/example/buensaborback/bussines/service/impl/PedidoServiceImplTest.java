package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.IArtManufacturadoService;
import com.example.buensaborback.bussines.service.IClienteService;
import com.example.buensaborback.bussines.service.IUsuarioService;
import com.example.buensaborback.domain.entities.Pedido;
import com.example.buensaborback.repositories.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
class PedidoServiceImplTest {
      PedidoRepository pedidoRepository;
      IUsuarioService usuarioService;
      IArtManufacturadoService artManufacturadoService;
      IClienteService clienteService;

    @Test
    void getPedidoById() {
    }

    @Test
    void existsPedidoById() {
    }

    @Test
    void save() {

    }

    @Test
    void getAll() {
    }

    @Test
    void getAllByFecha() {
    }

    @Test
    void delete() {
    }

    @Test
    void getAllByCliente() {
    }

    @Test
    void findTopProducts() {
    }

    @Test
    void findPedidosBetweenDates() {
    }

    private Pedido mockPedido(){
        Pedido pedido = Pedido.builder().build();

        return pedido;
    }
}