package com.example.service;

import com.example.model.Vacation;
import com.example.validator.VacationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VacationServiceImpl implements VacationService {
    /**
     * Число месяцев в году
     */
    private static final int MONTHS_IN_YEAR = 12;
    /**
     * Среднемесячное число календарных дней
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

    private final CalendarService calendarService;

    @Autowired
    public VacationServiceImpl(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @Override
    public Vacation calculateVacationPay(Vacation vacation) {
        VacationValidator.validate(vacation);

        int workingDays = calendarService.getVacationDays(vacation.getStartDate(), vacation.getEndDate());
        vacation.setAmount(getVacationAmount(vacation, workingDays));
        return vacation;
    }

    /**
     * Рассчитать средний дневной заработок (СДЗ)
     * @param vacation Отпуск
     * @return Средний дневной заработок (СДЗ)
     */
    private double getAvgDailySalary(Vacation vacation) {
        double avgDailySalary = vacation.getAvgSalary() / MONTHS_IN_YEAR / AVERAGE_DAYS_IN_MONTH;
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
