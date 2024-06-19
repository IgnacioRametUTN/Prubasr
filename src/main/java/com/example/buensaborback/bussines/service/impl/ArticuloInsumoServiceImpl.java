package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.*;
import com.example.buensaborback.domain.entities.*;
import com.example.buensaborback.presentation.advice.exception.BadRequestException;
import com.example.buensaborback.presentation.advice.exception.ImageUploadLimitException;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import com.example.buensaborback.repositories.ArticuloInsumoRepository;
import com.example.buensaborback.repositories.ImagenRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
public class ArticuloInsumoServiceImpl implements IArticuloInsumoService {
    private final ArticuloInsumoRepository articuloInsumoRepository;
    private final IUnidadMedidaService unidadMedidaService;
    private final ICategoriaService categoriaServiceImpl;
    private final IPromocionDetalleService promocionDetalleServiceImpl;
    private final ICloudinaryService cloudinaryService;
    private final ImagenRepository imagenRepository;

    public ArticuloInsumoServiceImpl(ArticuloInsumoRepository articuloInsumoRepository, UnidadMedidaServiceImpl unidadMedidaService, CategoriaServiceImpl categoriaServiceImpl, PromocionDetalleServiceImpl promocionDetalleServiceImpl, CloudinaryServiceImpl cloudinaryService, ImagenRepository imagenRepository) {
        this.articuloInsumoRepository = articuloInsumoRepository;
        this.unidadMedidaService = unidadMedidaService;
        this.categoriaServiceImpl = categoriaServiceImpl;
        this.promocionDetalleServiceImpl = promocionDetalleServiceImpl;
        this.cloudinaryService = cloudinaryService;
        this.imagenRepository = imagenRepository;
    }

    public ArticuloInsumo getArticuloInsumoById(Long id){
        return this.articuloInsumoRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Articulo Insumo con ID %d no encontrado", id)));
    }

    public boolean existsArticuloInsumoById(Long id){
        return this.articuloInsumoRepository.existsById(id);
    }

    public ArticuloInsumo create(ArticuloInsumo entity){
       entity.setCategoria(this.categoriaServiceImpl.getCategoriaById( entity.getCategoria().getId()));
       entity.setUnidadMedida(this.unidadMedidaService.getUnidadMedidaById( entity.getUnidadMedida().getId()));
        return this.articuloInsumoRepository.save(entity);
    }

    public ArticuloInsumo update(Long id,ArticuloInsumo entity) {
        this.getArticuloInsumoById(id); //Verifica si existe unicamente, sino larga expcion
        // Actualizar las referencias a UnidadMedida y Categoria
        entity.setUnidadMedida(unidadMedidaService.getUnidadMedidaById(entity.getUnidadMedida().getId()));
        entity.setCategoria(categoriaServiceImpl.getCategoriaById(entity.getCategoria().getId()));

        // Actualizar la lista de PromocionDetalle
        if(nonNull(entity.getPromocionDetalle())){
            entity.setPromocionDetalle(entity.getPromocionDetalle().stream()
                    .map(detalle -> {
                        PromocionDetalle promocionDetalle = promocionDetalleServiceImpl.getPromocionDetalleById(detalle.getId());
                        promocionDetalle.setArticulo(entity);
                        return promocionDetalle;
                    })
                    .collect(Collectors.toSet()));
        }

        // Guardar y devolver la entidad actualizada
        return articuloInsumoRepository.save(entity);
    }

    public ArticuloInsumo delete(Long id){
        ArticuloInsumo entity = this.getArticuloInsumoById(id);
        entity.setAlta(!entity.isAlta());
        return this.articuloInsumoRepository.save(entity);
    }
    public List<ArticuloInsumo> getAll(){
        return articuloInsumoRepository.findAll();
    }
    public List<ArticuloInsumo> getAll(Optional<Long> categoriaOpt, Optional<Long> unidadMedidaOpt, Optional<String> searchOpt) {
        Categoria categoria = categoriaOpt.map(categoriaServiceImpl::getCategoriaById).orElse(null); //Basicamente funciona así: si el Optional está vacío el map() no hace nada y salta al orElse y devuelve null, caso contrario ejecuta el metodo del map
        UnidadMedida unidadMedida = unidadMedidaOpt.map(unidadMedidaService::getUnidadMedidaById).orElse(null);
        String search = searchOpt.orElse("");

        if (categoria != null && unidadMedida != null) {
            return articuloInsumoRepository.findByCategoriaAndUnidadMedidaAndDenominacionStartingWithIgnoreCase(categoria, unidadMedida, search);
        } else if (categoria != null) {
            return articuloInsumoRepository.findByCategoriaAndDenominacionStartingWithIgnoreCase(categoria, search);
        } else if (unidadMedida != null) {
            return articuloInsumoRepository.findByUnidadMedidaAndDenominacionStartingWithIgnoreCase(unidadMedida, search);
        } else if (!search.isEmpty()) {
            return articuloInsumoRepository.findByDenominacionStartingWithIgnoreCase(search);
        } else {
            return articuloInsumoRepository.findAll();
        }
    }

    @Override
    public Set<Imagen> uploadImages(MultipartFile[] files, Long idArticuloInsumo) {
        List<String> urls = new ArrayList<>();
        var insumo = getArticuloInsumoById(idArticuloInsumo);
        //Se limita a un maximo de 3 imagenes por entidad
        if (insumo.getImagenes().size() > 3)
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

            //Se asignan las imagenes al insumo
            insumo.getImagenes().add(image);
            //Se guarda la imagen en la base de datos
            imagenRepository.save(image);
            // Agregar la URL de la imagen a la lista de URLs subidas
            urls.add(image.getUrl());
        }

        //se actualiza el insumo en la base de datos con las imagenes
        articuloInsumoRepository.save(insumo);

        // Convertir la lista de URLs a un objeto JSON y devolver como ResponseEntity con estado OK (200)
        return insumo.getImagenes();

    }

//    @Override
//    public List<Imagen> deleteImage(String publicId, Long id) {
//        try {
//            // Eliminar la imagen de la base de datos usando su identificador
//            imagenArticuloRepository.deleteById(id);
//
//            // Llamar al servicio de Cloudinary para eliminar la imagen por su publicId
//            return cloudinaryService.deleteImage(publicId, id);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            // Devolver un error (400) si ocurre alguna excepción durante la eliminación
//            return new ResponseEntity<>("{\"status\":\"ERROR\", \"message\":\"" + e.getMessage() + "\"}", HttpStatus.BAD_REQUEST);
//        }
//    }


}
