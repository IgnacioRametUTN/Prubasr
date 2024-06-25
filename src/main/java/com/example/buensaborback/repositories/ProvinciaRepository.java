package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Pais;
import com.example.buensaborback.domain.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Long>{
    List<Provincia> findByPais(Pais pais);
}
