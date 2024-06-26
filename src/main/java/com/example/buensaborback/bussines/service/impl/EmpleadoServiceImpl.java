package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.IEmpleadoService;
import com.example.buensaborback.domain.entities.Empleado;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import com.example.buensaborback.repositories.EmpleadoRepository;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoServiceImpl implements IEmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoServiceImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public Empleado getEmpeladoById(Long id) {
        return this.empleadoRepository.findById(id).orElseThrow(() -> new NotFoundException("Empleado no encontrado"));
    }

    @Override
    public Empleado create(Empleado empleado) {
        return this.empleadoRepository.save(empleado);
    }
}
