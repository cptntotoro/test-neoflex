package com.example.exception;

public class IncorrectVacationException extends RuntimeException {
    public IncorrectVacationException(String message) {
        super(message);
    }
}
