package com.example.buensaborback.presentation.advice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class ImageUploadLimitException extends RuntimeException{
    public ImageUploadLimitException(String message) {
        super(message);
    }
}