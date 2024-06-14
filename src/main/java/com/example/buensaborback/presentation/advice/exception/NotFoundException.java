package com.example.buensaborback.presentation.advice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
