package com.example.buensaborback.presentation.advice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class EntityInUseException extends RuntimeException {
    public EntityInUseException(String message) {
        super(message);
    }
}

