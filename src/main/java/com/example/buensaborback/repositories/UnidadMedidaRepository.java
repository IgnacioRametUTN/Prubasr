package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.UnidadMedida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
}
