package com.example.service;

import com.example.dto.CalendarOutDto;
import com.example.model.Vacation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VacationServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private VacationServiceImpl vacationService;

    private Vacation vacation;
    private CalendarOutDto calendarData;

    @BeforeEach
    void setUp() {
        vacation = new Vacation.Builder()
                .startDate(LocalDate.of(2025, 6, 1))
                .endDate(LocalDate.of(2025, 6, 14))
                .avgAnnualSalary(600_000)
                .build();

        calendarData = new CalendarOutDto.Builder()
                .workingDays(List.of(
                    LocalDate.of(2025, 6, 2),
                    LocalDate.of(2025, 6, 3),
                    LocalDate.of(2025, 6, 4),
                    LocalDate.of(2025, 6, 5),
                    LocalDate.of(2025, 6, 6),
                    LocalDate.of(2025, 6, 9),
                    LocalDate.of(2025, 6, 10),
                    LocalDate.of(2025, 6, 11),
                    LocalDate.of(2025, 6, 12),
                    LocalDate.of(2025, 6, 13)
            ))
                .build();
    }

    @Test
    void calculateVacationPay_ShouldReturnCorrectAmount() {
        when(restTemplate.getForObject(anyString(), eq(CalendarOutDto.class)))
                .thenReturn(calendarData);

        Vacation result = vacationService.calculateVacationPay(vacation);

        assertNotNull(result);
        assertEquals(10, calendarData.getWorkingDays().size());
        assertEquals(14846.42, result.getAmount());
    }

    @Test
    void calculateVacationPay_WithSalaryBelowMinimum_ShouldReturnMinimumAmount() {
        vacation = new Vacation.Builder()
                .startDate(LocalDate.of(2025, 6, 1))
                .endDate(LocalDate.of(2025, 6, 14))
                .avgAnnualSalary(1000) // Очень маленькая зарплата (ниже МРОТ)
                .build();

        when(restTemplate.getForObject(anyString(), eq(CalendarOutDto.class)))
                .thenReturn(calendarData);

        Vacation result = vacationService.calculateVacationPay(vacation);

        assertNotNull(result);
        assertEquals(10, calendarData.getWorkingDays().size());
        assertEquals(6663.07, result.getAmount(), 0.01);
    }

    @Test
    void getVacationAmount_ShouldCalculateCorrectly() {
        int workingDays = 10;
        double avgDailySalary = 600_000 / 12 / 29.3;
        double expectedAmount = avgDailySalary * workingDays * 0.87;

        double result = vacationService.getVacationAmount(vacation, workingDays);

        assertEquals(expectedAmount, result, 0.01);
    }
}