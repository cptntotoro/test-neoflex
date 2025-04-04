package com.example.dto;

import java.time.LocalDate;

/**
 * Запрос расчета отпуска
 */
public class VacationRequestDto extends BaseVacationDto {
    private VacationRequestDto(double avgAnnualSalary, LocalDate startDate, LocalDate endDate) {
        super(avgAnnualSalary, startDate, endDate);
    }

    /**
     * Построитель {@link VacationRequestDto}
     * @return Построитель
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder extends BaseVacationDto.Builder<Builder> {
        public VacationRequestDto build() {
            return new VacationRequestDto(avgAnnualSalary, startDate, endDate);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }
}
