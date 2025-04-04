package com.example.validator;

import com.example.exception.IncorrectCalendarException;
import com.example.model.Calendar;

import java.time.LocalDate;

public class CalendarValidator {
    /**
     * Проверить календарь
     *
     * @param calendar Калегдарь
     */
    public static void validate(Calendar calendar) {
        LocalDate startDate = calendar.getStartDate();
        LocalDate endDate = calendar.getEndDate();

        if (startDate == null) {
            throw new IncorrectCalendarException("Отсутствует дата начала");
        }
        if (endDate == null) {
            throw new IncorrectCalendarException("Отсутствует дата окончания");
        }
        if (endDate.isBefore(startDate)) {
            throw new IncorrectCalendarException("Дата начала должна быть раньше даты окончания");
        }
    }
}
