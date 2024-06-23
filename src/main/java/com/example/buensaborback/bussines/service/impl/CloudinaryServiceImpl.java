package com.example.buensaborback.bussines.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.buensaborback.bussines.service.ICloudinaryService;
import jakarta.annotation.Resource;
import org.cloudinary.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements ICloudinaryService {

    @Resource
    private Cloudinary cloudinary; // Inyección de dependencia de Cloudinary

    // Método para subir un archivo a Cloudinary
    @Override
    public String uploadFile(MultipartFile file) {
        try {
            HashMap<Object, Object> options = new HashMap<>();
            // Subir el archivo a Cloudinary y obtener la información de la respuesta
            Map uploadedFile = cloudinary.uploader().upload(file.getBytes(), options);
            String publicId = (String) uploadedFile.get("public_id");
            // Generar la URL segura de la imagen subida
            return cloudinary.url().secure(true).generate(publicId);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para eliminar una imagen de Cloudinary
    @Override
    public boolean deleteImage(String publicId) {
        try {
            // Eliminar la imagen en Cloudinary
            Map response = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            JSONObject json = new JSONObject(response);

            // Verificar si la eliminación fue exitosa
            return "ok".equals(json.getString("result"));
        } catch (Exception e) {
            e.printStackTrace();
            // Devolver un error en caso de excepción durante la eliminación
            return false;
        }
    }
}
