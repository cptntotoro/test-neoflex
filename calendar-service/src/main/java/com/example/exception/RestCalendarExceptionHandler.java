package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestCalendarExceptionHandler {

    @ExceptionHandler(IncorrectCalendarException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String exceptionNotFound(IncorrectCalendarException e, Model model) {
        String reason = "Неверный формат календаря. " + e.getMessage();
        RestApiCalendarError apiException = new RestApiCalendarError.Builder()
                .status(HttpStatus.BAD_REQUEST.toString())
                .reason(reason)
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        model.addAttribute("error", apiException);
        return "error";
    }
}
