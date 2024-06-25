package com.example.buensaborback.bussines.service;

import com.example.buensaborback.domain.entities.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IArtManufacturadoService {
     ArticuloManufacturado getArticuloManufacturadoById(Long id);
     boolean existsArticuloManufacturadoById(Long id);
     ArticuloManufacturado create(ArticuloManufacturado entity,Long idSucursal);
     ArticuloManufacturado update(Long id, ArticuloManufacturado entity);
     List<ArticuloManufacturado> getAll(Optional<Long> categoriaOpt, Optional<Long> unidadMedidaOpt, Optional<String> searchOpt,Long idSucursal);
     List<ArticuloManufacturado> getAll();
     ArticuloManufacturado delete(Long id);
     Set<Imagen> uploadImages(MultipartFile[] files, Long id);

     List<ArticuloManufacturado> findArtManufacturadosFromCategoryAndSubcategories(Long idSucursal, Long idCategoria);
}
