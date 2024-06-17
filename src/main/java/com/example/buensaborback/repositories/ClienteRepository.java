package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository  extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNombreContaining(String nombre);
    List<Cliente> findByApellidoContaining(String apellido);
    List<Cliente> findByNombreContainingAndApellidoContaining(String nombre, String apellido);

}
