package com.example.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

/**
 * Базовый DTO отпуска
 */
public abstract class BaseVacationDto {
    protected final double avgSalary;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    protected final LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    protected final LocalDate endDate;

    protected BaseVacationDto(double avgSalary, LocalDate startDate, LocalDate endDate) {
        this.avgSalary = avgSalary;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public double getAvgSalary() {
        return avgSalary;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Абстрактный построитель для наследников
     */
    public abstract static class Builder<T extends Builder<T>> {
        protected double avgSalary;
        protected LocalDate startDate;
        protected LocalDate endDate;

        public T avgSalary(double avgSalary) {
            this.avgSalary = avgSalary;
            return self();
        }

        public T startDate(LocalDate startDate) {
            this.startDate = startDate;
            return self();
        }

        public T endDate(LocalDate endDate) {
            this.endDate = endDate;
            return self();
        }

        protected abstract T self();
    }
}