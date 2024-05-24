package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.domain.entities.Categoria;
import com.example.buensaborback.domain.entities.UnidadMedida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ArticuloManufacturadoRepository extends BaseRepository<ArticuloManufacturado,Long> {
    List<ArticuloManufacturado> findByCategoriaAndUnidadMedida(Categoria categoria, UnidadMedida unidadMedida);

    List<ArticuloManufacturado> findByCategoria(Categoria categoria);

    List<ArticuloManufacturado> findByUnidadMedida(UnidadMedida unidadMedida);
}
