package com.example.converter;

import com.example.dto.CalendarInDto;
import com.example.dto.CalendarOutDto;
import com.example.model.Calendar;

public class CalendarDtoConverter {

    /**
     * Конвертировать входящее DTO календаря в календарь
     *
     * @param calendarInDto Входящее DTO календаря
     * @return Календарь
     */
    public static Calendar calendarInDtoToCalendar(CalendarInDto calendarInDto) {
        return new Calendar.Builder()
                .startDate(calendarInDto.getStartDate())
                .endDate(calendarInDto.getEndDate())
                .build();
    }

    /**
     * Конвертировать календарь в исходящее DTO календаря
     *
     * @param calendar Календарь
     * @return Исходящее DTO календаря
     */
    public static CalendarOutDto calendarToCalendarOutDto(Calendar calendar) {
        return new CalendarOutDto.Builder()
                .startDate(calendar.getStartDate())
                .endDate(calendar.getEndDate())
                .holidays(calendar.getHolidays())
                .workingDays(calendar.getWorkingDays())
                .nonWorkingDays(calendar.getNonWorkingDays())
                .build();

    }
}
