package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Articulo;
import com.example.buensaborback.domain.entities.Categoria;
import com.example.buensaborback.domain.entities.Sucursal;
import com.example.buensaborback.domain.entities.UnidadMedida;

import java.util.List;
public interface BusquedaRepository<T extends Articulo>{

    List<T> findBySucursalAndCategoriaAndUnidadMedidaAndDenominacionStartingWithIgnoreCase(Sucursal sucursal, Categoria categoria, UnidadMedida unidadMedida, String denominacion);


    List<T> findBySucursalAndCategoriaAndDenominacionStartingWithIgnoreCase(Sucursal sucursal, Categoria categoria, String denominacion);


    List<T> findBySucursalAndUnidadMedidaAndDenominacionStartingWithIgnoreCase(Sucursal sucursal, UnidadMedida unidadMedida, String denominacion);


    List<T> findBySucursalAndDenominacionStartingWithIgnoreCase(Sucursal sucursal, String denominacion);


    List<T> findBySucursal(Sucursal sucursal);

}
