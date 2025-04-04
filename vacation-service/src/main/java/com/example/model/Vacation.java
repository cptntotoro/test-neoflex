package com.example.model;

import java.time.LocalDate;

/**
 * Класс отпуска
 */
public class Vacation {
    private final double avgAnnualSalary;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private double amount;

    private Vacation(double avgAnnualSalary, LocalDate startDate, LocalDate endDate, double amount) {
        this.avgAnnualSalary = avgAnnualSalary;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
    }

    public double getAvgAnnualSalary() {
        return avgAnnualSalary;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Построитель {@link Vacation}
     * @return Построитель
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private double avgAnnualSalary;
        private LocalDate startDate;
        private LocalDate endDate;
        private double amount;

        /**
         * Установить среднюю зарплату
         * @param avgAnnualSalary Средняя зарплата
         */
        public Builder avgAnnualSalary(double avgAnnualSalary) {
            this.avgAnnualSalary = avgAnnualSalary;
            return this;
        }

        /**
         * Установить дату начала отпуска
         * @param startDate Дата начала отпуска
         */
        public Builder startDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        /**
         * Установить дату конца отпуска
         * @param endDate Дата конца отпуска
         */
        public Builder endDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        /**
         * Установить оплату отпуска
         * @param amount Оплата отпуска
         */
        public Builder amount(double amount) {
            this.amount = amount;
            return this;
        }

        public Vacation build() {
            return new Vacation(avgAnnualSalary, startDate, endDate, amount);
        }
    }
}