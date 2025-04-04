package com.example.exception;

/**
 * Исключение некорректного формата отпуска
 */
public class IncorrectVacationException extends RuntimeException {
    public IncorrectVacationException(String message) {
        super(message);
    }
}
