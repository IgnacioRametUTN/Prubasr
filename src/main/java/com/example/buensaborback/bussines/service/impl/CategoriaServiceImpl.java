package com.example.buensaborback.bussines.service.impl;


import com.example.buensaborback.bussines.service.ICategoriaService;
import com.example.buensaborback.domain.entities.Categoria;
import com.example.buensaborback.domain.entities.Sucursal;
import com.example.buensaborback.presentation.advice.exception.DuplicateEntryException;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import com.example.buensaborback.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements ICategoriaService {
    private final CategoriaRepository categoriaRepository;
    private final ISucursalServiceImpl sucursalService;
    @Autowired
    public CategoriaServiceImpl(CategoriaRepository categoriaRepository, ISucursalServiceImpl sucursalService) {
        this.categoriaRepository = categoriaRepository;
        this.sucursalService = sucursalService;
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
    public List<Categoria> findAllCategoriasPadre() {
        return this.categoriaRepository.findCategoriasPadres();
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
    public Categoria create(Long idPadre, Long idSucursal, Categoria body) {
        Sucursal sucursal = sucursalService.getSucursalById(idSucursal);

        if (existsCategoriaByDenominacion(body.getDenominacion())) {
            body = categoriaRepository.findByDenominacionIgnoreCase(body.getDenominacion());
            actualizarCategoriaExistente(body, sucursal);
        } else {
            if (idPadre != 0) {
                Categoria categoriaPadre = getCategoriaById(idPadre);
                body.setCategoriaPadre(categoriaPadre);
                categoriaPadre.getSubCategorias().add(body);
            }
            body.getSucursales().add(sucursal);
            sucursal.getCategorias().add(body);

            sucursalService.updateSucursal(sucursal.getId(), sucursal);
        }

        return categoriaRepository.save(body);
    }

    private void actualizarCategoriaExistente(Categoria body, Sucursal sucursal) {
        body.setAlta(true);
        body.getSucursales().add(sucursal);
        sucursal.getCategorias().add(body);

        if (body.getCategoriaPadre() != null) {
            body.getCategoriaPadre().getSucursales().add(sucursal);
            sucursal.getCategorias().add(body.getCategoriaPadre());
        }
    }



    @Override
    public Categoria delete(Long id,Long idSucursal) {
        Categoria categoria = this.getCategoriaById(id);
        Sucursal sucursal=this.sucursalService.getSucursalById((idSucursal));
        categoria.getSucursales().remove(sucursal);
        sucursal.getCategorias().remove(categoria);
        categoria.getSubCategorias().forEach(cat -> {
            cat.getSucursales().remove(sucursal);
            sucursal.getCategorias().remove(cat);
            this.categoriaRepository.save(cat);
        });
        if (categoria.getSucursales().isEmpty()) {
            boolean alta = false;
            categoria.setAlta(alta);
            categoria.getSubCategorias().forEach(cat -> {
                cat.setAlta(alta);
                cat.getSucursales().remove(sucursal);
                sucursal.getCategorias().remove(cat);
            });

        }
        this.sucursalService.saveSucursal(sucursal);
        return this.categoriaRepository.save(categoria);
    }

    @Override
    public List<Categoria> findAllBySucursal(Long id){
        return this.categoriaRepository.findAllBySucursal(id);
    }

    @Override
    public List<Categoria> findAllBySucu(Long id){
        return this.categoriaRepository.findAllBySucu(id);
    }
}
