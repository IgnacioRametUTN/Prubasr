package com.example.buensaborback.bussines.service.impl;


import com.example.buensaborback.bussines.service.ICategoriaService;
import com.example.buensaborback.domain.entities.Categoria;
import com.example.buensaborback.domain.entities.Categoria;
import com.example.buensaborback.presentation.advice.exception.DuplicateEntryException;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import com.example.buensaborback.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements ICategoriaService {
    private final CategoriaRepository categoriaRepository;
    @Autowired
    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }
    @Override
    public Categoria getCategoriaById(Long id){
        return this.categoriaRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Categoria con ID %d no encontrado", id)));
    }

    @Override
    public boolean existsCategoriaById(Long id){
        return this.categoriaRepository.existsById(id);
    }

    @Override
    public boolean existsCategoriaByDenominacion(String denominacion){
        return categoriaRepository.existsByDenominacionIgnoreCase(denominacion);
    }

    @Override
    public List<Categoria> findAll() {
        return this.categoriaRepository.findAll();
    }
    @Override
    public List<Categoria> findAllAlta() {
        return this.categoriaRepository.findByAltaTrue();
    }
    @Override
    public Categoria update(Long id, Categoria body) {
        Categoria unidadMedida = this.getCategoriaById(id);
        if(!unidadMedida.getDenominacion().equalsIgnoreCase(body.getDenominacion())){ //validacion para permitir que se pueda hacer  update HARINAS -> Harina
            if(existsCategoriaByDenominacion(body.getDenominacion())) throw new DuplicateEntryException(String.format("Ya existe una Categoria con el nombre %s", body.getDenominacion()));
        }
        return this.categoriaRepository.save(body);
    }
    @Override
    public Categoria create(Categoria body) {
        if(existsCategoriaByDenominacion(body.getDenominacion())) throw new DuplicateEntryException(String.format("Ya existe una Categoria con el nombre %s", body.getDenominacion()));
        return this.categoriaRepository.save(body);
    }
    @Override
    public Categoria delete(Long id) {
        Categoria categoria = this.getCategoriaById(id);
        categoria.setAlta(!categoria.isAlta());
        return this.categoriaRepository.save(categoria);
    }

    @Override
    public Categoria createSubCategoria(Long id, Categoria body) {
        Categoria categoriaPadre = this.getCategoriaById(id);
        boolean exists = categoriaPadre.getSubCategorias().stream()
                .anyMatch(categoria -> categoria.getDenominacion().equalsIgnoreCase(body.getDenominacion()));

        if (exists) {
            throw new DuplicateEntryException(String.format("Ya existe una SubCategoria dentro de %s con el nombre %s", categoriaPadre.getDenominacion() ,body.getDenominacion()));
        }

        categoriaPadre.getSubCategorias().add(body);
        return this.categoriaRepository.save(categoriaPadre); //TODO: revisar si devolver padre o hijo
    }
}
