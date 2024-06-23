package com.example.buensaborback.presentation.rest;

import com.example.buensaborback.bussines.service.ICategoriaService;
import com.example.buensaborback.bussines.service.impl.CategoriaServiceImpl;
import com.example.buensaborback.domain.entities.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin("*")
public class CategoriaController{

    private final ICategoriaService categoriaService;
    @Autowired
    public CategoriaController(CategoriaServiceImpl categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/all/{idSucursal}")
    public ResponseEntity<List<Categoria>> getAll(@PathVariable("idSucursal") Long idSucursal){
        return ResponseEntity.ok().body(this.categoriaService.findAllBySucu(idSucursal));
    }

    @GetMapping("/padres/{idSucursal}")
    public ResponseEntity<List<Categoria>> getAllCategoriasPadres(@PathVariable("idSucursal") Long idSucursal){
        return ResponseEntity.ok().body(this.categoriaService.findAllBySucursal(idSucursal));
    }

    @GetMapping("/alta")
    public ResponseEntity<List<Categoria>> getAllAlta(){
        return ResponseEntity.ok().body(this.categoriaService.findAllAlta());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getOne(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(this.categoriaService.getCategoriaById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> update(@PathVariable("id") Long id, @RequestBody Categoria body){
        return ResponseEntity.ok().body(this.categoriaService.update(id, body));
    }

    @PostMapping("/{idSucursal}/{idCategoriaPadre}")
    public ResponseEntity<Categoria> save(@PathVariable("idCategoriaPadre") Long idPadre,@PathVariable("idSucursal") Long idSucursal, @RequestBody Categoria body){
        return ResponseEntity.ok().body(this.categoriaService.create(idPadre, idSucursal,body));
    }

    /*@PostMapping("/bulk")
    public ResponseEntity<?> save(@RequestBody List<Categoria> bulk){
        bulk.forEach(categoria -> this.categoriaService.create(categoria.getCategoriaPadre().getId(), categoria));
        return ResponseEntity.ok().build();
    }*/

    @DeleteMapping("/{idSucursal}/{id}")
    public ResponseEntity<Categoria> delete(@PathVariable("idSucursal") Long idSucursal,@PathVariable("id") Long id){
        return ResponseEntity.ok().body(this.categoriaService.delete(id,idSucursal));
    }
}
    