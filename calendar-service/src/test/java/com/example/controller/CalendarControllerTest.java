package com.example.controller;

import com.example.model.Calendar;
import com.example.service.CalendarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CalendarController.class)
class CalendarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalendarService calendarService;

    @Test
    void getCalendarDays_ShouldReturnAcceptedStatus() throws Exception {
        LocalDate startDate = LocalDate.of(2025, 1, 1);
        LocalDate endDate = LocalDate.of(2025, 1, 10);

        Calendar mockCalendar = new Calendar(startDate, endDate, Set.of(), Set.of());
        when(calendarService.createCalendar(any(Calendar.class)))
                .thenReturn(mockCalendar);

        mockMvc.perform(get("/days")
                        .param("startDate", "01.01.2025")
                        .param("endDate", "10.01.2025"))
                .andExpect(status().isAccepted());
    }

    @Test
    void getCalendarDays_ShouldReturnCorrectData() throws Exception {
        LocalDate startDate = LocalDate.of(2025, 1, 1);
        LocalDate endDate = LocalDate.of(2025, 1, 3);
        Set<LocalDate> holidays = Set.of(LocalDate.of(2025, 1, 1));

        Calendar mockCalendar = new Calendar(startDate, endDate, holidays, Set.of());
        when(calendarService.createCalendar(any(Calendar.class)))
                .thenReturn(mockCalendar);

        mockMvc.perform(get("/days")
                        .param("startDate", "01.01.2025")
                        .param("endDate", "03.01.2025"))
                .andExpect(jsonPath("$.startDate").value("2025-01-01"))
                .andExpect(jsonPath("$.endDate").value("2025-01-03"))
                .andExpect(jsonPath("$.holidays[0]").value("2025-01-01"))
                .andExpect(jsonPath("$.workingDays.length()").value(2));
    }

    @Test
    void getCalendarDays_ShouldValidateDateFormat() throws Exception {
        mockMvc.perform(get("/days")
                        .param("startDate", "01-01-2025") // неправильный формат
                        .param("endDate", "10.01.2025"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getCalendarDays_ShouldRequireBothDates() throws Exception {
        mockMvc.perform(get("/days")
                        .param("startDate", "01.01.2025"))
                .andExpect(status().isBadRequest());
    }
}
