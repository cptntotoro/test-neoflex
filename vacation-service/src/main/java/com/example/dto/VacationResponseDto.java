package com.example.dto;

import java.time.LocalDate;

/**
 * Ответ расчета отпуска
 */
public class VacationResponseDto extends BaseVacationDto {
    private final double amount;

    private VacationResponseDto(double avgAnnualSalary, LocalDate startDate, LocalDate endDate, double amount) {
        super(avgAnnualSalary, startDate, endDate);
        this.amount = amount;
    }

    /**
     * Получить сумму отпускных выплат
     * @return Сумма отпускных выплат
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Построитель {@link VacationResponseDto}
     * @return Построитель
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends BaseVacationDto.Builder<Builder> {
        private double amount;

        /**
         * Установить сумму отпускных выплат
         * @param amount Сумма отпускных выплат
         */
        public Builder amount(double amount) {
            this.amount = amount;
            return this;
        }

        @Override
        protected Builder self() {
            return this;
        }

        public VacationResponseDto build() {
            return new VacationResponseDto(avgAnnualSalary, startDate, endDate, amount);
        }
    }
}
