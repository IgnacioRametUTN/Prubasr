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

        return this.categoriaRepository.save(categoria);
    }

    @Override
    public Categoria create(Long idPadre, Long idSucursal, Categoria body, List<Long> sucursalesIds) {
        // Obtener la sucursal principal sobre la que se está trabajando
        Sucursal sucursal = sucursalService.getSucursalById(idSucursal);
        System.out.println("Active sucursal " + sucursal.getNombre());
        System.out.println("idpadre " + idPadre);

        // Obtener la lista de sucursales asociadas a la categoría
        List<Sucursal> sucursales = sucursalService.getSucursalesByIds(sucursalesIds);
        System.out.println("Sucursales asociadas: " + sucursales);

        // Verificar si ya existe una categoría con dicho nombre
        if (existsCategoriaByDenominacion(body.getDenominacion())) {
            // Si existe, recuperar esa categoría en lugar de crear una nueva
            body = categoriaRepository.findByDenominacionIgnoreCase(body.getDenominacion());
            System.out.println("Categoría existente recuperada: " + body);
        } else {
            // Si no existe una categoría, se creará
            if (idPadre != null && idPadre != 0) {
                // Si se proporciona un idPadre y no es 0, es una subcategoría
                Categoria categoriaPadre = getCategoriaById(idPadre);
                System.out.println("Categoría padre encontrada: " + categoriaPadre);

                body.setCategoriaPadre(categoriaPadre);
            } else {
                // Si idPadre es null o 0, entonces es una categoría padre
                body.setCategoriaPadre(null); // No tiene categoría padre
                System.out.println("Categoría padre no asignada, es una categoría raíz.");
            }
        }

        // Actualizar la información de la categoría actual
        Categoria categoriaGuardada=actualizarCategoriaExistente(body, sucursal, sucursales);

        System.out.println("Categoría después de actualizar la información: " + body);
        System.out.println("Categoría guardada: " + categoriaGuardada);
        return categoriaGuardada;
    }

    private Categoria actualizarCategoriaExistente(Categoria body, Sucursal sucursal, List<Sucursal> sucursales) {
        System.out.println("Actualizando categoría existente...");
        body.setAlta(true);
        System.out.println("Sucursales antes de agregar nuevas: " + body.getSucursales());
        body.getSucursales().clear(); // Asegúrate de que no agregue duplicados
        if (!sucursales.contains(sucursal)){
            body.getSucursales().add(sucursal);
        }
        body.getSucursales().addAll(sucursales);
        System.out.println("Sucursales después de agregar nuevas: " + body.getSucursales());

        // Actualizar las sucursales con la nueva categoría
        for (Sucursal s : body.getSucursales()) {
            try {
                System.out.println("Actualizando sucursal con ID: " + s.getId());
                Sucursal actualizada = sucursalService.getSucursalById(s.getId());
                if (actualizada != null) {
                    if (!actualizada.getCategorias().contains(body)) {
                        if (categoriaRepository.existsByDenominacionIgnoreCase(body.getDenominacion())){
                            Categoria categoria = categoriaRepository.findByDenominacionIgnoreCase(body.getDenominacion());
                            actualizada.getCategorias().add(categoria);
                        }else {
                            actualizada.getCategorias().add(body);
                        }
                        sucursalService.updateSucursal(actualizada.getId(), actualizada);
                        System.out.println("Sucursal actualizada con nueva categoría: " + actualizada);
                    }
                } else {
                    System.err.println("Sucursal no encontrada: " + s.getId());
                }
            } catch (Exception e) {
                System.err.println("Error al actualizar la sucursal con ID: " + s.getId() + " - " + e.getMessage());
            }
        }
        System.out.println("Categoría después de actualizar sucursales: " + body);
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