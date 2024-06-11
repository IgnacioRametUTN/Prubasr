package com.example.buensaborback.presentation.advice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class InsufficientStock extends RuntimeException{
    public InsufficientStock(String message) {
        super(message);
    }
}

