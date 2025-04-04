package com.example.exception;

/**
 * Исключение некорректного формата календаря
 */
public class IncorrectCalendarException extends RuntimeException {
    public IncorrectCalendarException(String message) {
        super(message);
    }
}
