package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ArticuloManufacturadoRepository extends JpaRepository<ArticuloManufacturado, Long>{

    @Query("""
            select a from ArticuloManufacturado a inner join a.categoria.sucursales sucursales
            where sucursales.id = :idSucursal and a.categoria.id = :idCategoria and a.unidadMedida.id = :idUnidadMedida and upper(a.denominacion) like upper(concat(:denominacion, '%'))""")
    List<ArticuloManufacturado> findByCategoriaAndUnidadMedidaAndDenominacionStartingWithIgnoreCase(Long idCategoria, Long idUnidadMedida, String denominacion, Long idSucursal);

    @Query("""
            select a from ArticuloManufacturado a inner join a.categoria.sucursales sucursales
            where sucursales.id = :idSucursal and a.categoria.id = :idCategoria  and upper(a.denominacion) like upper(concat(:denominacion, '%'))""")
    List<ArticuloManufacturado> findByCategoriaAndDenominacionStartingWithIgnoreCase(Long idCategoria, String denominacion, Long idSucursal);

    @Query("""
            select a from ArticuloManufacturado a inner join a.categoria.sucursales sucursales
            where sucursales.id = :idSucursal and a.unidadMedida.id = :idUnidadMedida and upper(a.denominacion) like upper(concat(:denominacion, '%'))""")
    List<ArticuloManufacturado> findByUnidadMedidaAndDenominacionStartingWithIgnoreCase(Long idUnidadMedida, String denominacion, Long idSucursal);

    @Query("""
            select a from ArticuloManufacturado a inner join a.categoria.sucursales sucursales
            where sucursales.id = :idSucursal and upper(a.denominacion) like upper(concat(:denominacion, '%'))""")
    List<ArticuloManufacturado> findByDenominacionStartingWithIgnoreCase(String denominacion,Long idSucursal);


    @Query("""
            select a from ArticuloManufacturado a inner join a.categoria.sucursales sucursales
            where sucursales.id = :idSucursal""")
    List<ArticuloManufacturado> findAllBySucursal(Long idSucursal);


}
