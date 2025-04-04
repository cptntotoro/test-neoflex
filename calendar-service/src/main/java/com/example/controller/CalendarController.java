package com.example.controller;

import com.example.converter.CalendarDtoConverter;
import com.example.dto.CalendarInDto;
import com.example.dto.CalendarOutDto;
import com.example.model.Calendar;
import com.example.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class CalendarController {
    private CalendarService calendarService;

    @Autowired
    public void setCalendarService(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @GetMapping("/days")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public CalendarOutDto addRequest(@RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate startDate,
                                     @RequestParam @DateTimeFormat(pattern = "dd.MM.yyyy") LocalDate endDate) {
        CalendarInDto calendarInDto = new CalendarInDto.Builder()
                .startDate(startDate)
                .endDate(endDate)
                .build();

        Calendar calendar = calendarService.createCalendar(CalendarDtoConverter.calendarInDtoToCalendar(calendarInDto));
        return CalendarDtoConverter.calendarToCalendarOutDto(calendar);
    }
}
