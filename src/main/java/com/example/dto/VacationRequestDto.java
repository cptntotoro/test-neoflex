package com.example.dto;

import java.time.LocalDate;

/**
 * Запрос расчета отпуска
 */
public class VacationRequestDto extends BaseVacationDto {
    private VacationRequestDto(double avgSalary, LocalDate startDate, LocalDate endDate) {
        super(avgSalary, startDate, endDate);
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
            return new VacationRequestDto(avgSalary, startDate, endDate);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }
}
