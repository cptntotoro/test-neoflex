package com.example.converter;

import com.example.dto.VacationRequestDto;
import com.example.dto.VacationResponseDto;
import com.example.model.Vacation;

/**
 * Конвертер DTO отпусков
 */
public class VacationConverter {

    /**
     * Конвертировать DTO отпуска в модель отпуска
     * @param vacationRequestDto DTO отпуска
     * @return Модель отпуска
     */
    public static Vacation vacationRequestDtoToVacation(VacationRequestDto vacationRequestDto) {
        return new Vacation.Builder()
                .avgAnnualSalary(vacationRequestDto.getAvgAnnualSalary())
                .startDate(vacationRequestDto.getStartDate())
                .endDate(vacationRequestDto.getEndDate())
                .build();
    }

    /**
     * Конвертировать отпуска в DTO отпуска
     * @param vacation Модель отпуска
     * @return Ответ рассчета отпуска
     */
    public static VacationResponseDto vacationToVacationResponseDto(Vacation vacation) {
        return new VacationResponseDto.Builder()
                .startDate(vacation.getStartDate())
                .endDate(vacation.getEndDate())
                .avgAnnualSalary(vacation.getAvgAnnualSalary())
                .amount(vacation.getAmount())
                .build();
    }
}
