package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.ArticuloInsumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticuloInsumoRepository extends JpaRepository<ArticuloInsumo, Long> {
    @Query("""
            select a from ArticuloInsumo a inner join a.categoria.sucursales sucursales
            where sucursales.id = :idSucursal and a.categoria.id = :idCategoria and a.unidadMedida.id = :idUnidadMedida and upper(a.denominacion) like upper(concat(:denominacion, '%'))""")
    List<ArticuloInsumo> findByCategoriaAndUnidadMedidaAndDenominacionStartingWithIgnoreCase(Long idCategoria, Long idUnidadMedida, String denominacion, Long idSucursal);

    @Query("""
            select a from ArticuloInsumo a inner join a.categoria.sucursales sucursales
            where sucursales.id = :idSucursal and a.categoria.id = :idCategoria  and upper(a.denominacion) like upper(concat(:denominacion, '%'))""")
    List<ArticuloInsumo> buscarByCategoriaAndDenominacionDeSucursal(Long idCategoria, String denominacion, Long idSucursal);

    @Query("""
            select a from ArticuloInsumo a inner join a.categoria.sucursales sucursales
            where sucursales.id = :idSucursal and a.unidadMedida.id = :idUnidadMedida and upper(a.denominacion) like upper(concat(:denominacion, '%'))""")
    List<ArticuloInsumo> findByUnidadMedidaAndDenominacionStartingWithIgnoreCase(Long idUnidadMedida, String denominacion, Long idSucursal);

    @Query("""
            select a from ArticuloInsumo a inner join a.categoria.sucursales sucursales
            where sucursales.id = :idSucursal and upper(a.denominacion) like upper(concat(:denominacion, '%'))""")
    List<ArticuloInsumo> findByDenominacionStartingWithIgnoreCase(String denominacion,Long idSucursal);


    @Query("""
            select a from ArticuloInsumo a inner join a.categoria.sucursales sucursales
            where sucursales.id = :idSucursal""")
    List<ArticuloInsumo> findAllBySucursal(Long idSucursal);

}
