package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Articulo;
import com.example.buensaborback.domain.entities.Categoria;
import com.example.buensaborback.domain.entities.UnidadMedida;
import java.util.List;

public interface IBusquedaRepository<T extends Articulo>{
    List<T> findByCategoriaAndUnidadMedidaAndDenominacionStartingWithIgnoreCase(Categoria categoria, UnidadMedida unidadMedida, String denominacion);

    List<T> findByCategoriaAndDenominacionStartingWithIgnoreCase(Categoria categoria, String denominacion);

    List<T> findByUnidadMedidaAndDenominacionStartingWithIgnoreCase(UnidadMedida unidadMedida, String denominacion);

    List<T> findByDenominacionStartingWithIgnoreCase(String denominacion);
}
