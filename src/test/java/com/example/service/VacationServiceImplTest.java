package com.example.service;

import com.example.model.Vacation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VacationServiceImplTest {

    @Mock
    private CalendarService calendarService;

    @InjectMocks
    private VacationServiceImpl vacationService;

    private Vacation vacation;

    @BeforeEach
    void setUp() {
        vacation = new Vacation.Builder()
                .startDate(LocalDate.of(2025, 6, 1))
                .endDate(LocalDate.of(2025, 6, 14))
                .avgSalary(180_000.0)
                .build();
    }

    @Test
    void calculateVacationPay_ShouldReturnCorrectAmount() {
        int workingDays = 10;
        when(calendarService.getVacationDays(any(), any())).thenReturn(workingDays);

        Vacation result = vacationService.calculateVacationPay(vacation);

        assertNotNull(result);
        assertEquals(6663.07, result.getAmount(), 0.01);
        verify(calendarService).getVacationDays(vacation.getStartDate(), vacation.getEndDate());
    }

    @Test
    void getVacationAmount_ShouldCalculateCorrectly() {
        double result = vacationService.getVacationAmount(vacation, 14);

        assertEquals(9328.3, result, 0.01);
    }
}