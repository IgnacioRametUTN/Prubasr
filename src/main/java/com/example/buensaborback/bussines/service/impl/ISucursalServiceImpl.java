package com.example.buensaborback.bussines.service.impl;

import com.example.buensaborback.bussines.service.*;
import com.example.buensaborback.domain.entities.Domicilio;
import com.example.buensaborback.domain.entities.Imagen;
import com.example.buensaborback.domain.entities.Sucursal;

import com.example.buensaborback.presentation.advice.exception.BadRequestException;
import com.example.buensaborback.presentation.advice.exception.ImageUploadLimitException;
import com.example.buensaborback.presentation.advice.exception.NotFoundException;
import com.example.buensaborback.repositories.ImagenRepository;
import com.example.buensaborback.repositories.SucursalRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

@Service
public class ISucursalServiceImpl implements ISucursalService {

    private final SucursalRepository sucursalRepository;
    private final ICloudinaryService cloudinaryService;
    private final ImagenRepository imagenRepository;
    private final IImagenService imagenService;

    private final IEmpresaService empresaService;
    private final IDomicilioService domicilioService;
    public ISucursalServiceImpl(SucursalRepository sucursalRepository, CloudinaryServiceImpl cloudinaryService,
                                ImagenRepository imagenRepository, ImagenServiceImpl imagenService,
                                IEmpresaServiceImpl empresaService, IDomicilioService domicilioService) {
        this.sucursalRepository = sucursalRepository;
        this.cloudinaryService = cloudinaryService;
        this.imagenRepository = imagenRepository;
        this.imagenService = imagenService;
        this.empresaService = empresaService;
        this.domicilioService = domicilioService;
    }

    @Override
    public Sucursal saveSucursal(Sucursal sucursal) {
        if (sucursal == null) {
            throw new IllegalArgumentException("Sucursal no puede estar vacia");
        }

        if (sucursal.getEmpresa() == null || sucursal.getEmpresa().getId() == null) {
            throw new IllegalArgumentException("Ingresar empresa valida");
        }

        if (sucursal.getDomicilio() == null) {
            throw new IllegalArgumentException("Ingresar un domicilio valido");
        }

        System.out.println(sucursal);

        sucursal.setEmpresa(empresaService.getEmpresaById(sucursal.getEmpresa().getId()));
        sucursal.setDomicilio(domicilioService.create(sucursal.getDomicilio()));

        return sucursalRepository.save(sucursal);
    }

    @Override
    public Sucursal getSucursalById(Long id) {
        return sucursalRepository.findById(id).orElseThrow(() -> new NotFoundException("La Sucursal con id " + id + " no se encuntra"));
    }

    @Override
    public List<Sucursal> getAllSucursales() {
        return sucursalRepository.findAll();
    }

    @Override
    public Sucursal updateSucursal(Long id, Sucursal sucursal) {
        // Fetch the existing Sucursal
        Sucursal existingSucursal = getSucursalById(id);
        if (existingSucursal == null) {
            // Handle the case where the Sucursal does not exist
            throw new NotFoundException("No se encontro la sucursal: " + id);
        }

        // Update fields of the existing Sucursal
        existingSucursal.setNombre(sucursal.getNombre());
        existingSucursal.setHorarioApertura(sucursal.getHorarioApertura());
        existingSucursal.setHorarioCierre(sucursal.getHorarioCierre());

        // Update the Domicilio
        Domicilio updatedDomicilio = domicilioService.update(sucursal.getDomicilio().getId(), sucursal.getDomicilio());
        existingSucursal.setDomicilio(updatedDomicilio);

        // Update images if applicable
        imagenService.updateImagenes(existingSucursal.getImagenes(), sucursal.getImagenes());

        // Save and return the updated Sucursal
        return sucursalRepository.save(existingSucursal);
    }


    @Override
    public void deleteSucursal(Long id) {
        sucursalRepository.deleteById(id);
    }

    @Override
    public List<Sucursal> getSucursalesByEmpresaId(Long empresaId) {
        return sucursalRepository.findByEmpresaId(empresaId);
    }

    @Override
    public Set<Imagen> uploadImages(MultipartFile[] files, Long idArticuloInsumo) {
        Sucursal sucursal = getSucursalById(idArticuloInsumo);
        //Se limita a un maximo de 3 imagenes por entidad
        if (sucursal.getImagenes().size() > 3)
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
            sucursal.getImagenes().add(image);
            //Se guarda la imagen en la base de datos
            imagenRepository.save(image);
        }

        //se actualiza el insumo en la base de datos con las imagenes
        sucursalRepository.save(sucursal);

        return sucursal.getImagenes();
    }
}
