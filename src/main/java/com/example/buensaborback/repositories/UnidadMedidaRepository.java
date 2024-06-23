package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.UnidadMedida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UnidadMedidaRepository extends JpaRepository<UnidadMedida, Long> {
    @Transactional
    @Modifying
    @Query("update UnidadMedida u set u.denominacion = ?1 where u.denominacion = ?2")
    int updateDenominacionByDenominacion(String denominacion, String denominacion1);

    List<UnidadMedida> findByAltaTrue();

    boolean existsByDenominacionIgnoreCaseAllIgnoreCase(String denominacion);

    boolean existsByDenominacionIgnoreCase(String denominacion);


    @Query("SELECT CASE WHEN COUNT(ai) > 0 THEN true ELSE false END FROM ArticuloInsumo ai WHERE ai.unidadMedida.id = :unidadMedida AND ai.alta = true ")
    boolean existsInArticuloInsumoByUnidadMedida(@Param("unidadMedida") Long unidadMedida);

    @Query("SELECT CASE WHEN COUNT(am) > 0 THEN true ELSE false END FROM ArticuloManufacturado am WHERE am.unidadMedida.id = :unidadMedida AND am.alta = true")
    boolean existsInArticuloManufacturadoByUnidadMedida(@Param("unidadMedida") Long unidadMedida);

}
