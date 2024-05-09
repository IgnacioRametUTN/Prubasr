package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinciaRepository extends BaseRepository<Provincia,Long> {
}
