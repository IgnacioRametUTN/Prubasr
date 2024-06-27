package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.bussines.service.IArticuloInsumoService;
import com.example.buensaborback.bussines.service.impl.ArticuloInsumoServiceImpl;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.domain.entities.ArticuloManufacturado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/articulos/insumos")
@CrossOrigin("*")
public class ArticuloInsumoController {

    private final IArticuloInsumoService articuloInsumoService;

    @Autowired
    public ArticuloInsumoController(ArticuloInsumoServiceImpl articuloInsumoService) {
        this.articuloInsumoService = articuloInsumoService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok().body(this.articuloInsumoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(this.articuloInsumoService.getArticuloInsumoById(id));
    }

    @PostMapping("/{idSucursal}")
    public ResponseEntity<?> create( @PathVariable("idSucursal") Long idSucursal,@RequestBody ArticuloInsumo body){
        return ResponseEntity.ok().body(this.articuloInsumoService.create(body,idSucursal));
    }


    @PostMapping("/uploads")
    public ResponseEntity<?> uploadImages(@RequestParam(value = "id") Long idArticulo,
                                          @RequestParam(value = "uploads") MultipartFile[] files) {
        return ResponseEntity.ok(articuloInsumoService.uploadImages(files, idArticulo));

    }


    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ArticuloInsumo body){
        return ResponseEntity.ok().body(this.articuloInsumoService.update(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(this.articuloInsumoService.delete(id));
    }


    @GetMapping("/{sucursal_id}/search")
    public ResponseEntity<?> getAll(@PathVariable("sucursal_id") Long idSucursal,
                                    @RequestParam("categoria_id") Optional<Long> categoria,
                                    @RequestParam("unidad_id") Optional<Long> unidadMedida,
                                    @RequestParam("denominacion") Optional<String> denominacion){
        return ResponseEntity.ok().body(this.articuloInsumoService.getAll(idSucursal, categoria, unidadMedida, denominacion));
    }

    @GetMapping("/{idSucursal}/categoria/{idCategoria}")
    public ResponseEntity<List<ArticuloInsumo>> getArticuloManufacturadoParaCategoriaYSubcategorias
            (@PathVariable("idSucursal") Long idSucursal, @PathVariable("idCategoria") Long idCategoria){
        return ResponseEntity.ok().body(this.articuloInsumoService.findArtInsumoFromCategoryAndSubcategories(idSucursal, idCategoria));
    }

}
    