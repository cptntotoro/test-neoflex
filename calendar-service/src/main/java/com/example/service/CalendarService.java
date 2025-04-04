package com.example.service;

import com.example.model.Calendar;

/**
 * Сервис календаря
 */
public interface CalendarService {

    /**
     * Получить полный календарь
     * @param calendar Календарь
     * @return Календарь
     */
    Calendar createCalendar(Calendar calendar);
}
