package com.example.buensaborback.presentation.advice.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
@AllArgsConstructor
public class DuplicateEntryException extends RuntimeException{
    private Object object;
    public DuplicateEntryException(String message) {
        super(message);
    }


}