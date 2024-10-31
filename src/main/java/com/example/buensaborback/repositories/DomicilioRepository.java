package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Domicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DomicilioRepository  extends JpaRepository<Domicilio, Long>{
    List<Domicilio> findByAltaTrue();
}
