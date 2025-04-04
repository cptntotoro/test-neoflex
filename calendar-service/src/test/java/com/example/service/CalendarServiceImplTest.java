package com.example.service;

import com.example.model.Calendar;
import com.example.validator.CalendarValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CalendarServiceImplTest {

    @Mock
    private CalendarValidator calendarValidator;

    @InjectMocks
    private CalendarServiceImpl calendarService;

    private Calendar testCalendar;

    @BeforeEach
    void setUp() {
        testCalendar = new Calendar.Builder()
                .startDate(LocalDate.of(2025, 1, 1))
                .endDate(LocalDate.of(2025, 1, 20))
                .build();
    }

    @Test
    void createCalendar_ShouldReturnCalendarWithHolidays() {
        LocalDate startDate = LocalDate.of(2025, 1, 1);
        LocalDate endDate = LocalDate.of(2025, 1, 20);
        Calendar inputCalendar = new Calendar.Builder()
                .startDate(startDate)
                .endDate(endDate)
                .build();

        Calendar result = calendarService.createCalendar(inputCalendar);

        assertNotNull(result);
        assertEquals(startDate, result.getStartDate());
        assertEquals(endDate, result.getEndDate());

        List<LocalDate> holidays = result.getHolidays();
        assertNotNull(holidays);

        // Проверяем конкретные праздники
        assertTrue(holidays.contains(LocalDate.of(2025, 1, 1)));
        assertTrue(holidays.contains(LocalDate.of(2025, 1, 2)));
        assertTrue(holidays.contains(LocalDate.of(2025, 1, 3)));
        assertTrue(holidays.contains(LocalDate.of(2025, 1, 6)));
        assertTrue(holidays.contains(LocalDate.of(2025, 1, 7)));

        // Проверяем общее количество (11 фиксированных праздников + выходные в периоде)
        assertTrue(holidays.size() >= 11);
    }

    @Test
    void createCalendar_ShouldIncludeWeekendsAsHolidays() {
        Calendar weekendCalendar = new Calendar.Builder()
                .startDate(LocalDate.of(2025, 1, 11)) // Суббота
                .endDate(LocalDate.of(2025, 1, 12))   // Воскресенье
                .build();

        Calendar result = calendarService.createCalendar(weekendCalendar);

        List<LocalDate> holidays = result.getHolidays();
        assertTrue(holidays.contains(LocalDate.of(2025, 1, 11))); // Суббота
        assertTrue(holidays.contains(LocalDate.of(2025, 1, 12))); // Воскресенье
    }
}