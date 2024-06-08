package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.bussines.facade.IArticuloInsumoFacade;
import com.example.buensaborback.bussines.facade.impl.ArticuloInsumoFacadeImpl;
import com.example.buensaborback.bussines.service.impl.ArticuloInsumoServiceImpl;
import com.example.buensaborback.domain.dtos.articulos.insumo.ArticuloInsumoDto;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/articulos/insumos")
@CrossOrigin("*")
public class ArticuloInsumoController {

    private final ArticuloInsumoServiceImpl articuloInsumoService;

    @Autowired
    public ArticuloInsumoController(ArticuloInsumoServiceImpl articuloInsumoService) {
        this.articuloInsumoService = articuloInsumoService;
    }

    @GetMapping("/search")
    public ResponseEntity<?> getAll(@RequestParam("categoria_id") Optional<Long> categoria,
                                    @RequestParam("unidad_id") Optional<Long> unidadMedida,
                                    @RequestParam("denominacion") Optional<String> denominacion){
        return ResponseEntity.ok().body(this.articuloInsumoService.getAll(categoria, unidadMedida, denominacion));
    }


}
    