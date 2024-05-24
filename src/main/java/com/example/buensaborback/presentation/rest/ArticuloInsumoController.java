package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.bussines.facade.IArticuloInsumoFacade;
import com.example.buensaborback.bussines.facade.impl.ArticuloInsumoFacadeImpl;
import com.example.buensaborback.domain.dtos.articulos.insumo.ArticuloInsumoDto;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/articulos/insumos")
@CrossOrigin("*")
public class ArticuloInsumoController extends GenericControllerImpl<ArticuloInsumo, ArticuloInsumoDto,Long, ArticuloInsumoFacadeImpl> {
    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    private final IArticuloInsumoFacade articuloInsumoFacade;
    public ArticuloInsumoController(ArticuloInsumoFacadeImpl facade) {
        super(facade);
        this.articuloInsumoFacade = facade;
    }

    @GetMapping("/search")
    public ResponseEntity<?> getAll(@RequestParam("categoria_id") Optional<Long> categoria,
                                    @RequestParam("unidad_id") Optional<Long> unidadMedida,
                                    @RequestParam("denominacion") Optional<String> denominacion){
        return ResponseEntity.ok().body(this.articuloInsumoFacade.getArticulosInsumos(categoria, unidadMedida, denominacion));
    }


}
    