package com.example.buensaborback.bussines.service.impl;


import com.example.buensaborback.bussines.service.ICategoriaService;
import com.example.buensaborback.bussines.service.ICloudinaryService;
import com.example.buensaborback.bussines.service.IImagenService;
import com.example.buensaborback.domain.entities.ArticuloInsumo;
import com.example.buensaborback.domain.entities.Categoria;
import com.example.buensaborback.domain.entities.Imagen;
import com.example.buensaborback.domain.entities.Sucursal;
import com.example.buensaborback.presentation.advice.exception.BadRequestException;
import com.example.buensaborback.presentation.advice.exception.DuplicateEntryException;
import com.example.buensaborback.presentation.advice.exception.ImageUploadLimitException;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import com.example.buensaborback.repositories.CategoriaRepository;
import com.example.buensaborback.repositories.ImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;
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
        Categoria categoria = this.getCategoriaById(id);
        if(!categoria.getDenominacion().equalsIgnoreCase(body.getDenominacion())){ //validacion para permitir que se pueda hacer  update HARINAS -> Harina
            if(existsCategoriaByDenominacion(body.getDenominacion())) throw new DuplicateEntryException(String.format("Ya existe una Categoria con el nombre %s", body.getDenominacion()));
        }
        //Verificar Imagenes
        imagenService.updateImagenes(categoria.getImagenes(), body.getImagenes());
        return this.categoriaRepository.save(body);
    }
    @Override
    public Categoria create(Long idPadre, Long idSucursal, Categoria body) {
        Sucursal sucursal = sucursalService.getSucursalById(idSucursal);

        if (existsCategoriaByDenominacion(body.getDenominacion())) {
            body = categoriaRepository.findByDenominacionIgnoreCase(body.getDenominacion());
            System.out.println("Ya existe");
        } else {
            if (idPadre != 0) {
                System.out.println("No existe y es hija");
                Categoria categoriaPadre = getCategoriaById(idPadre);
                body.setCategoriaPadre(categoriaPadre);
                categoriaPadre.getSubCategorias().add(body);
            }
            System.out.println("No existe y es padre");
           // sucursalService.updateSucursal(sucursal.getId(), sucursal);
        }
        actualizarCategoriaExistente(body, sucursal);
        String denominacion = body.getDenominacion();
        return sucursalService.saveSucursal(sucursal).getCategorias().stream().filter(categoria -> categoria.getDenominacion().equals(denominacion)).findFirst().orElse(null);
    }

    private void actualizarCategoriaExistente(Categoria body, Sucursal sucursal) {
        body.setAlta(true);
        body.getSucursales().add(sucursal);
        sucursal.getCategorias().add(body);

        if (body.getCategoriaPadre() != null) {
            body.getCategoriaPadre().setAlta(true);
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

    @Override
    public Set<Imagen> uploadImages(MultipartFile[] files, Long idArticuloInsumo) {
        Categoria categoria = getCategoriaById(idArticuloInsumo);
        //Se limita a un maximo de 3 imagenes por entidad
        if (categoria.getImagenes().size() > 3)
            throw new ImageUploadLimitException("La maxima cantidad de imagens a subir son 3");

        // Iterar sobre cada archivo recibido
        for (MultipartFile file : files) {
            // Verificar si el archivo está vacío
            if (file.isEmpty()) {
                throw new BadRequestException("El archivo esta vacio");
            }

            // Crear una entidad Image y establecer su nombre y URL (subida a Cloudinary)
            Imagen image = new Imagen();
            image.setName(file.getOriginalFilename()); // Establecer el nombre del archivo original
            image.setUrl(cloudinaryService.uploadFile(file)); // Subir el archivo a Cloudinary y obtener la URL

            // Verificar si la URL de la imagen es nula (indicativo de fallo en la subida)
            if (image.getUrl() == null) {
                throw new BadRequestException("Hubo un problema al guardar la imagen");
            }

            //Se asignan las imagenes al categoria
            categoria.getImagenes().add(image);
            //Se guarda la imagen en la base de datos
            imagenRepository.save(image);
        }

        //se actualiza el categoria en la base de datos con las imagenes
        categoriaRepository.save(categoria);

        return categoria.getImagenes();
    }
}
