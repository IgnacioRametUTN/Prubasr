package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long>{
    Optional<Factura> findById(Long facturaId);
}
