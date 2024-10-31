package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Localidad;
import com.example.buensaborback.domain.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalidadRepository extends JpaRepository<Localidad, Long>{
    List<Localidad> findByProvincia(Provincia provincia);
}
