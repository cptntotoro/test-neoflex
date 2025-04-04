package com.example.service;

import com.example.dto.CalendarOutDto;
import com.example.model.Vacation;
import com.example.validator.VacationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class VacationServiceImpl implements VacationService {
    /**
     * Число месяцев в году
     */
    private static final int MONTHS_IN_YEAR = 12;
    /**
     * Среднемесячное число календарных дней в месяце
     */
    private static final double AVERAGE_DAYS_IN_MONTH = 29.3;
    /**
     * Минимальный размер оплаты труда (МРОТ) в России 2025
     */
    private static final double MINIMUM_WAGE_2025 = 22_440;
    /**
     * Минимальный среднедневной заработок
     */
    private static final double MINIMUM_DAILY_WAGE_2025 = MINIMUM_WAGE_2025 / AVERAGE_DAYS_IN_MONTH;
    /**
     * Ставка НДФЛ
     */
    private static final double NDFL = 0.13;

    private final RestTemplate restTemplate;

    @Autowired
    public VacationServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Vacation calculateVacationPay(Vacation vacation) {
        VacationValidator.validate(vacation);
        String url = String.format(
                "http://calendar-service:9090/days?startDate=%s&endDate=%s",
                vacation.getStartDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                vacation.getEndDate().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        );

        CalendarOutDto calendarData = restTemplate.getForObject(url, CalendarOutDto.class);
        int workingDaysCount = 0;

        List<LocalDate> workingDays;
        if (calendarData != null && calendarData.getWorkingDays() != null) {
            workingDays = calendarData.getWorkingDays();
            workingDaysCount = workingDays.size();
        }
        vacation.setAmount(getVacationAmount(vacation, workingDaysCount));
        return vacation;
    }

    /**
     * Рассчитать средний дневной заработок (СДЗ)
     * @param vacation Отпуск
     * @return Средний дневной заработок (СДЗ)
     */
    private double getAvgDailySalary(Vacation vacation) {
        double avgDailySalary = vacation.getAvgAnnualSalary() / MONTHS_IN_YEAR / AVERAGE_DAYS_IN_MONTH;
        return Math.max(avgDailySalary, MINIMUM_DAILY_WAGE_2025);
    }

    /**
     * Рассчитать отпускные выплаты за вычетом НДФЛ
     * @param vacation Отпуск
     * @return Отпускные выплаты
     */
    public double getVacationAmount(Vacation vacation, int workingDays) {
        double avgDailySalary = getAvgDailySalary(vacation);
        double vacationPay = avgDailySalary * workingDays * (1 - NDFL);
        return Math.round(vacationPay * 100) / 100.0;
    }
}
