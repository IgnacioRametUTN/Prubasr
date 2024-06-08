package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.bussines.facade.IArticuloInsumoFacade;
import com.example.buensaborback.bussines.facade.IArticuloManufacturadoFacade;
import com.example.buensaborback.bussines.facade.impl.ArticuloManufacturadoFacadeImpl;
import com.example.buensaborback.bussines.facade.impl.ClienteFacadeImpl;
import com.example.buensaborback.bussines.service.impl.ArticuloManufacturadoServiceImpl;
import com.example.buensaborback.domain.dtos.articulos.manufacturado.ArticuloManufacturadoDto;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/articulos/manufacturados")
@CrossOrigin("*")
public class ArticuloManufacturadoController{

    private final ArticuloManufacturadoServiceImpl articuloManufacturadoService;

    @Autowired
    public ArticuloManufacturadoController(ArticuloManufacturadoServiceImpl articuloManufacturadoService) {
        this.articuloManufacturadoService = articuloManufacturadoService;
    }

    @GetMapping("/search")
    public ResponseEntity<?> getAll(@RequestParam("categoria_id") Optional<Long> categoria,
                                    @RequestParam("unidad_id") Optional<Long> unidadMedida,
                                    @RequestParam("denominacion") Optional<String> denominacion){
        return ResponseEntity.ok().body(this.articuloManufacturadoService.getArticulosInsumos(categoria, unidadMedida, denominacion));
    }

}