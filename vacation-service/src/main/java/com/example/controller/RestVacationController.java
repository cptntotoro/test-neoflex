package com.example.controller;

import com.example.converter.VacationConverter;
import com.example.dto.VacationRequestDto;
import com.example.dto.VacationResponseDto;
import com.example.model.Vacation;
import com.example.service.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class RestVacationController {
    private final VacationService calculatorService;

    @Autowired
    public RestVacationController(VacationService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/calculate")
    public VacationResponseDto calculateVacationPay(@RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate startDate,
                                                    @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate endDate,
                                                    @RequestParam Double avgAnnualSalary) {
        VacationRequestDto vacationRequest = new VacationRequestDto.Builder()
                .startDate(startDate)
                .endDate(endDate)
                .avgAnnualSalary(avgAnnualSalary)
                .build();

        Vacation vacationToProcess = VacationConverter.vacationRequestDtoToVacation(vacationRequest);
        Vacation vacationProcessed = calculatorService.calculateVacationPay(vacationToProcess);

        return VacationConverter.vacationToVacationResponseDto(vacationProcessed);
    }
}