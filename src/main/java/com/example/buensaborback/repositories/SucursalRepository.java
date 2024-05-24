package com.example.buensaborback.repositories;

import com.example.buensaborback.domain.entities.Empresa;
import com.example.buensaborback.domain.entities.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SucursalRepository extends BaseRepository<Sucursal,Long> {

    List<Sucursal> findSucursalByEmpresa(Empresa empresa);
}
