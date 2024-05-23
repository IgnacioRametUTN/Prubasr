package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.bussines.service.impl.SucursalServiceImpl;
import com.example.buensaborback.domain.dtos.sucursal.SucursalFullDto;
import com.example.buensaborback.domain.entities.Sucursal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/sucursales")
public class SucursalController {

    @Autowired
    private SucursalServiceImpl sucursalService;

    @GetMapping
    public List<SucursalFullDto> getAll() {
        List<Sucursal> sucursales = sucursalService.findAll();
        return sucursales.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public Sucursal create(@RequestBody Sucursal sucursal) {
        return sucursalService.save(sucursal);
    }

    @PutMapping("/{id}")
    public SucursalFullDto update(@PathVariable Long id, @RequestBody SucursalFullDto sucursalFullDto) {
        Sucursal sucursal = convertToEntity(sucursalFullDto);
        sucursal.setId(id);
        return convertToDto(sucursalService.save(sucursal));
    }

    private SucursalFullDto convertToDto(Sucursal sucursal) {
        SucursalFullDto sucursalFullDto = new SucursalFullDto();
        sucursalFullDto.setId(sucursal.getId());
        sucursalFullDto.setNombre(sucursal.getNombre());
        sucursalFullDto.setHorarioApertura(sucursal.getHorarioApertura());
        sucursalFullDto.setHorarioCierre(sucursal.getHorarioCierre());
        return sucursalFullDto;
    }

    private Sucursal convertToEntity(SucursalFullDto sucursalFullDto) {
        Sucursal sucursal = new Sucursal();
        sucursal.setId(sucursalFullDto.getId());
        sucursal.setNombre(sucursalFullDto.getNombre());
        sucursal.setHorarioApertura(sucursalFullDto.getHorarioApertura());
        sucursal.setHorarioCierre(sucursalFullDto.getHorarioCierre());
        return sucursal;
    }
}
