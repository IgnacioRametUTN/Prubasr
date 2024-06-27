package com.example.buensaborback.bussines.service;

import com.example.buensaborback.domain.entities.Empleado;

public interface IEmpleadoService {

    Empleado getEmpeladoById(Long id);
    Empleado create(Empleado empleado);
}
