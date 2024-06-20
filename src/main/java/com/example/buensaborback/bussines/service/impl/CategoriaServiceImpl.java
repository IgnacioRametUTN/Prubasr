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
        // Obtener la sucursal y asegurarse de que esté gestionada por el contexto de persistencia

        Sucursal sucursal = sucursalService.getSucursalById(idSucursal);
        System.out.println("Sucursal que trae: " + sucursal.getNombre());

        // Agregar la sucursal a la categoría
        body.getSucursales().add(sucursal);

        if (idPadre == 0) {
            // Establecer la categoría padre como null para categorías raíz
            body.setCategoriaPadre(null);

            // Verificar duplicados
            if (existsCategoriaByDenominacion(body.getDenominacion())) {
                throw new DuplicateEntryException(String.format("Ya existe una Categoria con el nombre %s", body.getDenominacion()));
            }

            // Guardar la categoría raíz
            Categoria createCat = this.categoriaRepository.save(body);

            // Agregar la categoría a las categorías de la sucursal
            sucursal.getCategorias().add(createCat);

            // Guardar la sucursal para actualizar la relación
            sucursalService.updateSucursal(sucursal.getId(),sucursal);

            System.out.println("sucursal id: " + createCat.getSucursales().toString());
            return createCat;
        } else {
            // Obtener la categoría padre
            Categoria categoriaPadre = this.getCategoriaById(idPadre);

            // Verificar duplicados
            boolean exists = categoriaPadre.getSubCategorias().stream()
                    .anyMatch(categoria -> categoria.getDenominacion().equalsIgnoreCase(body.getDenominacion()));
            if (exists) {
                throw new DuplicateEntryException(String.format("Ya existe una SubCategoria dentro de %s con el nombre %s", categoriaPadre.getDenominacion(), body.getDenominacion()));
            }

            // Guardar la nueva categoría primero
            Categoria savedBody = this.categoriaRepository.save(body);

            // Agregar la nueva categoría guardada a la categoría padre
            categoriaPadre.getSubCategorias().add(savedBody);
            savedBody.setCategoriaPadre(categoriaPadre);

            // Guardar la categoría padre para actualizar la relación
            this.categoriaRepository.save(categoriaPadre);

            // Agregar la categoría a las categorías de la sucursal
            sucursal.getCategorias().add(savedBody);

            // Guardar la sucursal para actualizar la relación
            sucursalService.updateSucursal(sucursal.getId(),sucursal);

            System.out.println("sucursal id: " + savedBody.getSucursales().toString());
            return savedBody;
        }
    }

    @Override
    public Categoria delete(Long id) {
        Categoria categoria = this.getCategoriaById(id);
        boolean alta = !categoria.isAlta();
        categoria.setAlta(alta);
        categoria.getSubCategorias().forEach(cat -> cat.setAlta(alta));
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
