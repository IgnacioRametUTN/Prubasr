package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends BaseRepository<Pais,Long> {
}
