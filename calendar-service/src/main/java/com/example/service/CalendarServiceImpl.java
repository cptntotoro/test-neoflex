package com.example.service;

import com.example.model.Calendar;
import com.example.validator.CalendarValidator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
public class CalendarServiceImpl implements CalendarService {
    private final Set<LocalDate> holidays;

    public CalendarServiceImpl() {
        this.holidays = getFixedHolidays2025();
    }

    @Override
    public Calendar createCalendar(Calendar calendar) {
        CalendarValidator.validate(calendar);
        return new Calendar.Builder()
                .startDate(calendar.getStartDate())
                .endDate(calendar.getEndDate())
                .holidays(holidays)
                .build();
    }

    private Set<LocalDate> getFixedHolidays2025() {
        return Set.of(
                LocalDate.of(2025, 1, 1),  // Новый год
                LocalDate.of(2025, 1, 2),
                LocalDate.of(2025, 1, 3),
                LocalDate.of(2025, 1, 6),
                LocalDate.of(2025, 1, 7),  // Рождество
                LocalDate.of(2025, 2, 23), // День защитника Отечества
                LocalDate.of(2025, 3, 8),  // Международный женский день
                LocalDate.of(2025, 5, 1),  // Праздник весны и труда
                LocalDate.of(2025, 5, 9),  // День Победы
                LocalDate.of(2025, 6, 12), // День России
                LocalDate.of(2025, 11, 4)  // День народного единства
        );
    }
}