package com.example.buensaborback.presentation.rest;

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

    private final CategoriaServiceImpl categoriaServiceImpl;
    @Autowired
    public CategoriaController(CategoriaServiceImpl categoriaServiceImpl) {
        this.categoriaServiceImpl = categoriaServiceImpl;
    }

    @GetMapping("")
    public ResponseEntity<List<Categoria>> getAll(){
        return ResponseEntity.ok().body(this.categoriaServiceImpl.findAll());
    }

    @GetMapping("/alta")
    public ResponseEntity<List<Categoria>> getAllAlta(){
        return ResponseEntity.ok().body(this.categoriaServiceImpl.findAllAlta());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Categoria> getOne(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(this.categoriaServiceImpl.getCategoriaById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> update(@PathVariable("id") Long id, @RequestBody Categoria body){
        return ResponseEntity.ok().body(this.categoriaServiceImpl.update(id, body));
    }

    @PostMapping("")
    public ResponseEntity<Categoria> save(@RequestBody Categoria body){
        return ResponseEntity.ok().body(this.categoriaServiceImpl.create(body));
    }

    @PostMapping("/subcategoria/{idCategoriaPadre}")
    public ResponseEntity<Categoria> saveSubCategoria(@PathVariable("idCategoriaPadre") Long id, @RequestBody Categoria body){
        return ResponseEntity.ok().body(this.categoriaServiceImpl.createSubCategoria(id, body));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Categoria> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(this.categoriaServiceImpl.delete(id));
    }
}
    