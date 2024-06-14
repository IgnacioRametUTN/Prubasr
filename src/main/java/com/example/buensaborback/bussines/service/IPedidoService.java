package com.example.buensaborback.bussines.service;

import com.example.buensaborback.domain.entities.Articulo;
import com.example.buensaborback.domain.entities.Pedido;

import java.time.LocalDate;
import java.util.List;

public interface IPedidoService {

    Pedido getPedidoById(Long id);
    boolean existsPedidoById(Long id);
    Pedido save(Pedido pedido);
    List<Pedido> getAll();
    List<Pedido> getAllByCliente(Long idCliente);
    List<Pedido> getAllByFecha(LocalDate fecha);
    Pedido delete(Long id);
    List<Object> findTopProducts(LocalDate startDate, LocalDate endDate);

    List<Pedido> findPedidosBetweenDates(LocalDate startDate, LocalDate endDate);
}
