package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Cliente;
import com.example.buensaborback.domain.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PedidoRepository  extends JpaRepository<Pedido, Long> {
    List<Pedido> findByFechaPedido(LocalDate fecha);

    List<Pedido> findByAltaTrueAndCliente(Cliente cliente);
}
