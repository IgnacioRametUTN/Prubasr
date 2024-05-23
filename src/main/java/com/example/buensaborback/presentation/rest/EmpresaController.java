package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.domain.dtos.empresa.EmpresaDTO;
import com.example.buensaborback.domain.entities.Empresa;
import com.example.buensaborback.bussines.service.impl.EmpresaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/empresas")
@CrossOrigin("*")
public class EmpresaController {
    @Autowired
    private EmpresaServiceImpl empresaService;

    @GetMapping
    public List<EmpresaDTO> getAll() {
        List<Empresa> empresas = empresaService.findAll();
        // Convertir las entidades Empresa a DTOs EmpresaDTO
        return empresas.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public EmpresaDTO create(@RequestBody EmpresaDTO empresaDTO) {
        Empresa empresa = convertToEntity(empresaDTO);
        return convertToDto(empresaService.save(empresa));
    }

    @PutMapping("/{id}")
    public EmpresaDTO update(@PathVariable Long id, @RequestBody EmpresaDTO empresaDTO) {
        Empresa empresa = convertToEntity(empresaDTO);
        empresa.setId(id); // Establecer el ID de la empresa a actualizar
        return convertToDto(empresaService.save(empresa));
    }

    // Método para convertir Empresa a EmpresaDTO
    private EmpresaDTO convertToDto(Empresa empresa) {
        EmpresaDTO empresaDTO = new EmpresaDTO();
        empresaDTO.setId(empresa.getId());
        empresaDTO.setNombre(empresa.getNombre());
        empresaDTO.setRazonSocial(empresa.getRazonSocial());
        empresaDTO.setCuil(empresa.getCuil());
        return empresaDTO;
    }

    // Método para convertir EmpresaDTO a Empresa
    private Empresa convertToEntity(EmpresaDTO empresaDTO) {
        Empresa empresa = new Empresa();
        empresa.setId(empresaDTO.getId());
        empresa.setNombre(empresaDTO.getNombre());
        empresa.setRazonSocial(empresaDTO.getRazonSocial());
        empresa.setCuil(Long.valueOf(empresaDTO.getCuil()));
        return empresa;
    }
}
