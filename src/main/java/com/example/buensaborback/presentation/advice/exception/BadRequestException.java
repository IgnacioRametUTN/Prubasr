package com.example.buensaborback.presentation.advice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}

