package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.ICategoriaService;
import com.example.buensaborback.bussines.service.ICloudinaryService;
import com.example.buensaborback.bussines.service.IImagenService;
import com.example.buensaborback.domain.entities.Categoria;
import com.example.buensaborback.domain.entities.Imagen;
import com.example.buensaborback.domain.entities.Sucursal;
import com.example.buensaborback.presentation.advice.exception.BadRequestException;
import com.example.buensaborback.presentation.advice.exception.DuplicateEntryException;
import com.example.buensaborback.presentation.advice.exception.ImageUploadLimitException;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import com.example.buensaborback.repositories.CategoriaRepository;
import com.example.buensaborback.repositories.ImagenRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoriaServiceImpl implements ICategoriaService {
    private final CategoriaRepository categoriaRepository;
    private final ISucursalServiceImpl sucursalService;
    private final ICloudinaryService cloudinaryService;
    private final ImagenRepository imagenRepository;
    private final IImagenService imagenService;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository, ISucursalServiceImpl sucursalService,
                                CloudinaryServiceImpl cloudinaryService, ImagenRepository imagenRepository,
                                ImagenServiceImpl imagenService) {
        this.categoriaRepository = categoriaRepository;
        this.sucursalService = sucursalService;
        this.cloudinaryService = cloudinaryService;
        this.imagenService = imagenService;
        this.imagenRepository = imagenRepository;
    }

    @Override
    public Categoria getCategoriaById(Long id) {
        return this.categoriaRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Categoria con ID %d no encontrado", id)));
    }

    @Override
    public boolean existsCategoriaById(Long id) {
        return this.categoriaRepository.existsById(id);
    }

    @Override
    public boolean existsCategoriaByDenominacion(String denominacion) {
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
    public Categoria update(Long id, Categoria body, List<Long> sucursalesIds) {
        Categoria categoria = this.getCategoriaById(id);
        List<Sucursal> sucursales = sucursalService.getSucursalesByIds(sucursalesIds);

        if(!categoria.getDenominacion().equalsIgnoreCase(body.getDenominacion())) {
            if(existsCategoriaByDenominacion(body.getDenominacion())) {
                throw new DuplicateEntryException(String.format("Ya existe una Categoria con el nombre %s", body.getDenominacion()));
            }
        }

        // Update category properties
        categoria.setDenominacion(body.getDenominacion());
        categoria.setAlta(body.isAlta());


        // Update sucursales
        categoria.getSucursales().clear();
        categoria.getSucursales().addAll(sucursales);

        // Update images
        imagenService.updateImagenes(categoria.getImagenes(), body.getImagenes());
        Categoria categoriaGuardada=actualizarCategoriaExistente(body, sucursales);
        return categoriaGuardada;
    }

    @Override
    public Categoria create(Long idPadre, Long idSucursal, Categoria body, List<Long> sucursalesIds) {
        Sucursal sucursal = sucursalService.getSucursalById(idSucursal);
        List<Sucursal> sucursales = sucursalService.getSucursalesByIds(sucursalesIds);
        Categoria categoriaExistente = null;
        if (existsCategoriaByDenominacion(body.getDenominacion())) {
            categoriaExistente = categoriaRepository.findByDenominacionIgnoreCase(body.getDenominacion());
        }

        if (categoriaExistente != null) {
            body = categoriaExistente;
        } else {
            if (idPadre != null && idPadre != 0) {
                Categoria categoriaPadre = getCategoriaById(idPadre);
                body.setCategoriaPadre(categoriaPadre);
            } else {
                body.setCategoriaPadre(null);
            }
            body = categoriaRepository.save(body);
        }
        return actualizarCategoriaExistente(body, sucursales);
    }


    private Categoria actualizarCategoriaExistente(Categoria body, List<Sucursal> nuevasSucursales) {

        body.setAlta(true);

        List<Sucursal> sucursalesAntiguas = new ArrayList<>(body.getSucursales());

        List<Sucursal> sucursalesAEliminar = new ArrayList<>(sucursalesAntiguas);
        List<Sucursal> sucursalesAAgregar = new ArrayList<>(nuevasSucursales);

        sucursalesAEliminar.removeAll(nuevasSucursales);
        sucursalesAAgregar.removeAll(sucursalesAntiguas);

        for (Sucursal sucursalAntigua : sucursalesAEliminar) {
            try {
                Sucursal sucursalActualizada = sucursalService.getSucursalById(sucursalAntigua.getId());

                if (sucursalActualizada != null) {
                    sucursalActualizada.getCategorias().remove(this.getCategoriaById(body.getId()));
                    sucursalService.updateSucursal(sucursalActualizada.getId(), sucursalActualizada);
                } else {
                    System.err.println("Sucursal no encontrada para eliminación de relación: " + sucursalAntigua.getId());
                }
            } catch (Exception e) {
                System.err.println("Error al eliminar la relación con la sucursal antigua con ID: " + sucursalAntigua.getId() + " - " + e.getMessage());
            }
        }

        for (Sucursal nuevaSucursal : sucursalesAAgregar) {
            try {
                Sucursal sucursalActualizada = sucursalService.getSucursalById(nuevaSucursal.getId());

                if (sucursalActualizada != null) {
                    sucursalActualizada.getCategorias().add(body);

                    sucursalService.updateSucursal(sucursalActualizada.getId(), sucursalActualizada);
                } else {
                    System.err.println("Sucursal no encontrada: " + nuevaSucursal.getId());
                }
            } catch (Exception e) {
                System.err.println("Error al actualizar la sucursal con ID: " + nuevaSucursal.getId() + " - " + e.getMessage());
            }
        }

        body.getSucursales().clear();
        body.getSucursales().addAll(nuevasSucursales);

        return body;
    }


    @Override
    public Categoria delete(Long id, Long idSucursal) {
        Categoria categoria = this.getCategoriaById(id);
        Sucursal sucursal = this.sucursalService.getSucursalById((idSucursal));
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
    public List<Categoria> findAllBySucursal(Long id) {
        return this.categoriaRepository.findAllBySucursal(id);
    }

    @Override
    public List<Categoria> findAllBySucu(Long id) {
        return this.categoriaRepository.findAllBySucu(id);
    }

    @Override
    public Set<Imagen> uploadImages(MultipartFile[] files, Long idArticuloInsumo) {
        Categoria categoria = getCategoriaById(idArticuloInsumo);
        if (categoria.getImagenes().size() > 3)
            throw new ImageUploadLimitException("La maxima cantidad de imagens a subir son 3");

        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                throw new BadRequestException("El archivo esta vacio");
            }

            Imagen image = new Imagen();
            image.setName(file.getOriginalFilename());
            image.setUrl(cloudinaryService.uploadFile(file));

            if (image.getUrl() == null) {
                throw new BadRequestException("Hubo un problema al guardar la imagen");
            }

            categoria.getImagenes().add(image);
            imagenRepository.save(image);
        }

        categoriaRepository.save(categoria);

        return categoria.getImagenes();
    }
}