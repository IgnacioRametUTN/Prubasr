package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import org.springframework.data.jpa.repository.JpaRepository;

//No extiende de BaseRepository porque ArticuloManufacturado extiende de Articulo
// y Articulo NO extiende de Base porque necesita una estrategia de generación de ID diferente
public interface ArticuloManufacturadoRepository extends BaseArticuloRepository<ArticuloManufacturado,Long> {
}
