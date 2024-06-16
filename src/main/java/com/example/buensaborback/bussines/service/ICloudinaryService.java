package com.example.buensaborback.bussines.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ICloudinaryService {
    String uploadFile(MultipartFile file);
    ResponseEntity<String> deleteImage(String publicId, Long id);
}
