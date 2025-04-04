package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class VacationExceptionHandler {

    @ExceptionHandler(IncorrectVacationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public String incorrectVacationException(IncorrectVacationException e, Model model) {
        String reason = "Неверный формат запроса на отпуск." + e.getMessage();
        VacationError apiException = new VacationError.Builder()
                .status(HttpStatus.BAD_REQUEST.toString())
                .reason(reason)
                .message(e.getMessage())
                .timestamp(LocalDateTime.now())
                .build();
        model.addAttribute("error", apiException);
        return "error";
    }
}
