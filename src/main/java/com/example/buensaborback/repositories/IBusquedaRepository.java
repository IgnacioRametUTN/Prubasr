package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Articulo;
import com.example.buensaborback.domain.entities.Categoria;
import com.example.buensaborback.domain.entities.UnidadMedida;

import java.util.List;

public interface IBusquedaRepository<T extends Articulo>{
    /*SELECT a.* FROM ARTICULO_MANUFACTURADO a
    JOIN categoria c ON a.CATEGORIA_ID = c.id
    JOIN UNIDAD_MEDIDA um ON a.UNIDAD_MEDIDA_ID = um.id
            WHERE
    c.id =  2 AND
    um.id =2 AND
    LOWER(a.denominacion) LIKE LOWER('p%');*/
    List<T> findByCategoriaAndUnidadMedidaAndDenominacionStartingWithIgnoreCase(Categoria categoria, UnidadMedida unidadMedida, String denominacion);

    List<T> findByCategoriaAndDenominacionStartingWithIgnoreCase(Categoria categoria, String denominacion);

    List<T> findByUnidadMedidaAndDenominacionStartingWithIgnoreCase(UnidadMedida unidadMedida, String denominacion);

    List<T> findByDenominacionStartingWithIgnoreCase(String denominacion);
}
