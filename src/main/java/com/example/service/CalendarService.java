package com.example.service;

import java.time.LocalDate;

/**
 * Сервис календаря
 */
public interface CalendarService {

    /**
     * Получить число дней отпуска за интервал дат
     * @param startDate Дата начала отпуска
     * @param endDate Дата окончания отпуска
     * @return Число дней отпуска
     */
    int getVacationDays(LocalDate startDate, LocalDate endDate);
}
