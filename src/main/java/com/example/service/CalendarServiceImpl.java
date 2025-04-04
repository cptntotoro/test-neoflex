package com.example.service;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Service
public class CalendarServiceImpl implements CalendarService {
    private final Set<LocalDate> holidays;

    public CalendarServiceImpl() {
        holidays = getHolidays2025();
    }

    @Override
    public int getVacationDays(LocalDate startDate, LocalDate endDate) {
        return calculateWorkingDays(startDate, endDate);
    }

    private Set<LocalDate> getHolidays2025() {
        Set<LocalDate> holidays = new HashSet<>();

        // Фиксированные праздники (ст. 112 ТК РФ)
        holidays.add(LocalDate.of(2025, 1, 1));  // Новый год
        holidays.add(LocalDate.of(2025, 1, 2));
        holidays.add(LocalDate.of(2025, 1, 3));
        holidays.add(LocalDate.of(2025, 1, 6));
        holidays.add(LocalDate.of(2025, 1, 7));  // Рождество
        holidays.add(LocalDate.of(2025, 1, 8));  // Перенос с 4 января (суббота)

        holidays.add(LocalDate.of(2025, 2, 23)); // День защитника Отечества
        holidays.add(LocalDate.of(2025, 2, 24)); // Перенос с 3 января (пятница)

        holidays.add(LocalDate.of(2025, 3, 8));     // Международный женский день
        holidays.add(LocalDate.of(2025, 3, 10));   // Перенос с 5 января (воскресенье)

        holidays.add(LocalDate.of(2025, 5, 1));       // Праздник весны и труда
        holidays.add(LocalDate.of(2025, 5, 9));      // День Победы
        holidays.add(LocalDate.of(2025, 6, 12));    // День России
        holidays.add(LocalDate.of(2025, 11, 4)); // День народного единства

        return holidays;
    }

    /**
     * Расчет рабочих дней в интервале (исключая выходные и праздники)
     * @param startDate - начальная дата (включительно)
     * @param endDate - конечная дата (включительно)
     * @return количество рабочих дней
     */
    private int calculateWorkingDays(LocalDate startDate, LocalDate endDate) {
        int workingDays = 0;

        LocalDate current = startDate;
        while (!current.isAfter(endDate)) {
            if (!isWeekend(current) && !holidays.contains(current)) {
                workingDays++;
            }
            current = current.plusDays(1);
        }

        return workingDays;
    }

    private boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY
                || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

}
