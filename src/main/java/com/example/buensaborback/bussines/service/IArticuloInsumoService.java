package com.example.buensaborback.bussines.service;

import com.example.buensaborback.domain.entities.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IArticuloInsumoService {
     ArticuloInsumo getArticuloInsumoById(Long id);
     boolean existsArticuloInsumoById(Long id);
     ArticuloInsumo create(ArticuloInsumo entity);
     ArticuloInsumo update(Long id,ArticuloInsumo entity);
     ArticuloInsumo delete(Long id);
     List<ArticuloInsumo> getAll();
     List<ArticuloInsumo> getAll(Optional<Long> categoriaOpt, Optional<Long> unidadMedidaOpt, Optional<String> searchOpt);

     Set<Imagen> uploadImages(MultipartFile[] files, Long id);
     // Método para eliminar una imagen por su identificador público y Long
     //List<Imagen> deleteImage(String publicId, Long id);
}
