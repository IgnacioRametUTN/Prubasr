package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Articulo;
import com.example.buensaborback.domain.entities.Cliente;
import com.example.buensaborback.domain.entities.Pedido;
import com.example.buensaborback.domain.entities.enums.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PedidoRepository  extends JpaRepository<Pedido, Long> {
    List<Pedido> findByFechaPedido(LocalDate fecha);

    List<Pedido> findByAltaTrueAndCliente(Cliente cliente);

    @Query("SELECT a.denominacion, SUM(dp.cantidad) AS totalCantidad " +
            "FROM Pedido p " +
            "JOIN p.detallePedidos dp " +
            "JOIN dp.articulo a " +
            "WHERE p.fechaPedido BETWEEN :startDate AND :endDate " +
            "GROUP BY a.denominacion " +
            "ORDER BY totalCantidad DESC")
    List<Object> findTopProducts(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    List<Pedido> findByFechaPedidoBetween(LocalDate startDate, LocalDate endDate);

    List<Pedido> findByEstado(Estado estado);

}
