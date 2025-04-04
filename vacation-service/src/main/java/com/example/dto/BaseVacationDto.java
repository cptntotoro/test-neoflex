package com.example.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

/**
 * Базовый DTO отпуска
 */
public abstract class BaseVacationDto {
    protected final double avgAnnualSalary;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    protected final LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    protected final LocalDate endDate;

    protected BaseVacationDto(double avgAnnualSalary, LocalDate startDate, LocalDate endDate) {
        this.avgAnnualSalary = avgAnnualSalary;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public double getAvgAnnualSalary() {
        return avgAnnualSalary;
    }

    /**
     * Получить дату начала отпуска
     * @return Дата начала отпуска
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Получить дату окончания отпуска
     * @return Дата окончания отпуска
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Абстрактный построитель для наследников
     */
    public abstract static class Builder<T extends Builder<T>> {
        protected double avgAnnualSalary;
        protected LocalDate startDate;
        protected LocalDate endDate;

        /**
         * Установить среднюю зарплату
         * @param avgAnnualSalary Средняя зарплата
         */
        public T avgAnnualSalary(double avgAnnualSalary) {
            this.avgAnnualSalary = avgAnnualSalary;
            return self();
        }

        /**
         * Установить дату начала отпуска
         * @param startDate Дата начала отпуска
         */
        public T startDate(LocalDate startDate) {
            this.startDate = startDate;
            return self();
        }

        /**
         * Установить дату конца отпуска
         * @param endDate Дата конца отпуска
         */
        public T endDate(LocalDate endDate) {
            this.endDate = endDate;
            return self();
        }

        protected abstract T self();
    }
}