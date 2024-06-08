package com.example.buensaborback.bussines.service.impl;


import com.example.buensaborback.domain.entities.Categoria;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import com.example.buensaborback.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl {
    private final CategoriaRepository categoriaRepository;
    @Autowired
    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria getCategoriaById(Long id){
        return this.categoriaRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Categoria con ID %d no encontrado", id)));
    }

    public boolean existsCategoriaById(Long id){
        return this.categoriaRepository.existsById(id);
    }
}
