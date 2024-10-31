package com.example.buensaborback.presentation.advice.exception;

public class UnauthorizeException extends RuntimeException{
    public UnauthorizeException(String message) {
        super(message);
    }
}