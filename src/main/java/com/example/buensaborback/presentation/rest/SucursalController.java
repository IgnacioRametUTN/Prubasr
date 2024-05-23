package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.bussines.service.impl.SucursalServiceImpl;
import com.example.buensaborback.domain.dtos.sucursal.SucursalFullDto;
import com.example.buensaborback.domain.entities.Empresa;
import com.example.buensaborback.domain.entities.Pedido;
import com.example.buensaborback.domain.entities.Sucursal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/sucursales")
public class SucursalController {

    @Autowired
    private SucursalServiceImpl sucursalService;

    @GetMapping
    public List<SucursalFullDto> getAll() {
        System.out.println("por Todos");
        List<Sucursal> sucursales = sucursalService.findAll();
        return sucursales.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public List<SucursalFullDto> getAllByEmpresaId(@PathVariable Long id) {
        // Obtener todas las sucursales de la empresa con el ID proporcionado
        System.out.println("por ID");
        List<Sucursal> sucursales = sucursalService.findByEmpresaId(id);
        // Convertir las sucursales a DTO y devolver la lista
        return sucursales.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public SucursalFullDto create(@RequestBody SucursalFullDto sucursal) {

        return convertToDto(sucursalService.save(convertToEntity(sucursal)));
    }

    @PutMapping("/{id}")
    public SucursalFullDto update(@PathVariable Long id, @RequestBody SucursalFullDto sucursalFullDto) {
        // Obtener la sucursal existente de la base de datos
        Sucursal sucursal = sucursalService.findById(id);
        if (sucursal == null) {
            // Manejar el caso en que no se encuentre la sucursal
            // Aquí puedes lanzar una excepción o devolver un error adecuado
            return null;
        }

        // Actualizar los atributos de la sucursal con los valores del DTO
        sucursal.setNombre(sucursalFullDto.getNombre());
        sucursal.setHorarioApertura(LocalTime.parse(sucursalFullDto.getHorarioApertura(), DateTimeFormatter.ISO_LOCAL_TIME));
        sucursal.setHorarioCierre(LocalTime.parse(sucursalFullDto.getHorarioCierre(), DateTimeFormatter.ISO_LOCAL_TIME));

        // Actualizar la referencia de la sucursal en cada pedido
        sucursal.getPedidos().forEach(pedido -> pedido.setSucursal(sucursal));

        // Guardar la sucursal actualizada
        return convertToDto(sucursalService.save(sucursal));
    }

    private SucursalFullDto convertToDto(Sucursal sucursal) {
        SucursalFullDto sucursalFullDto = new SucursalFullDto();
        sucursalFullDto.setId(sucursal.getId());
        sucursalFullDto.setNombre(sucursal.getNombre());
        sucursalFullDto.setHorarioApertura(sucursal.getHorarioApertura().toString());
        sucursalFullDto.setHorarioCierre(sucursal.getHorarioCierre().toString());
        return sucursalFullDto;
    }

    private Sucursal convertToEntity(SucursalFullDto sucursalFullDto) {
        Sucursal sucursal = new Sucursal();
        sucursal.setId(sucursalFullDto.getId());
        sucursal.setNombre(sucursalFullDto.getNombre());
        // Convertir horarioApertura de String a LocalTime
        LocalTime horarioApertura = LocalTime.parse(sucursalFullDto.getHorarioApertura(), DateTimeFormatter.ISO_LOCAL_TIME);
        sucursal.setHorarioApertura(horarioApertura);

        // Convertir horarioCierre de String a LocalTime
        LocalTime horarioCierre = LocalTime.parse(sucursalFullDto.getHorarioCierre(), DateTimeFormatter.ISO_LOCAL_TIME);
        sucursal.setHorarioCierre(horarioCierre);
        System.out.println(sucursalFullDto.getIdEmpresa());
        Empresa empresa=new Empresa();
        empresa.setId(sucursalFullDto.getIdEmpresa());
        sucursal.setEmpresa(empresa);

        return sucursal;
    }
}
