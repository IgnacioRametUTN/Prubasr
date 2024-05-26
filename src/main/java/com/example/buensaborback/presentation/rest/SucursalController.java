package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.bussines.facade.impl.SucursalFacadeImpl;
import com.example.buensaborback.bussines.service.impl.SucursalServiceImpl;
import com.example.buensaborback.domain.dtos.sucursal.SucursalDto;
import com.example.buensaborback.domain.entities.Sucursal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/sucursales")
public class SucursalController  extends GenericControllerImpl<Sucursal, SucursalDto,Long, SucursalFacadeImpl>  {

    @Autowired
    private SucursalServiceImpl sucursalService;

    public SucursalController(SucursalFacadeImpl facade) {
        super(facade);
    }


    @GetMapping("/empresa/{id}")
    public ResponseEntity<List<SucursalDto>> getAllByEmpresaId(@PathVariable Long id) {
        return ResponseEntity.ok().body(this.facade.findSucursalesByEmpresaId(id));
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<SucursalDto> update(@PathVariable Long id, @RequestBody SucursalDto sucursalDto) {

        return ResponseEntity.ok().body(this.facade.update(sucursalDto,id));
    }


}
