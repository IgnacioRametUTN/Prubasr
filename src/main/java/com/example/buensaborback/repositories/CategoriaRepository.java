package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository  extends JpaRepository<Categoria, Long>{
    List<Categoria> findByAltaTrue();

    boolean existsByDenominacionIgnoreCase(String denominacion);
    @Query("SELECT c FROM Categoria c WHERE c.categoriaPadre IS NULL")
    List<Categoria> findCategoriasPadres();
}
