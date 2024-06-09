package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Articulo;
import com.example.buensaborback.domain.entities.Categoria;
import com.example.buensaborback.domain.entities.UnidadMedida;

import java.util.List;

public interface IBusquedaRepository<T extends Articulo>{
    //Query = SELECT * FROM categoria c WHERE c.id = id AND c.unidad_medida
    List<T> findByCategoriaAndUnidadMedidaAndDenominacionStartingWithIgnoreCase(Categoria categoria, UnidadMedida unidadMedida, String denominacion);

    List<T> findByCategoriaAndDenominacionStartingWithIgnoreCase(Categoria categoria, String denominacion);

    List<T> findByUnidadMedidaAndDenominacionStartingWithIgnoreCase(UnidadMedida unidadMedida, String denominacion);

    List<T> findByDenominacionStartingWithIgnoreCase(String denominacion);
}
