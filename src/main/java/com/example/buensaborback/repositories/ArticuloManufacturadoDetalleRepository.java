package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.ArticuloManufacturadoDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticuloManufacturadoDetalleRepository extends JpaRepository<ArticuloManufacturadoDetalle, Long>{
}
