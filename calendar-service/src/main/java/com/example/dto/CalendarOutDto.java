package com.example.dto;

import java.time.LocalDate;
import java.util.List;

/**
 * Класс исходящего DTO календаря
 */
public class CalendarOutDto extends CalendarBaseDto {
    private final List<LocalDate> holidays;
    private final List<LocalDate> nonWorkingDays;
    private final List<LocalDate> workingDays;

    public CalendarOutDto(LocalDate startDate, LocalDate endDate, List<LocalDate> holidays,
                          List<LocalDate> nonWorkingDays, List<LocalDate> workingDays) {
        super(startDate, endDate);
        this.holidays = holidays;
        this.nonWorkingDays = nonWorkingDays;
        this.workingDays = workingDays;
    }

    public List<LocalDate> getHolidays() {
        return holidays;
    }

    public List<LocalDate> getNonWorkingDays() {
        return nonWorkingDays;
    }

    public List<LocalDate> getWorkingDays() {
        return workingDays;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private LocalDate startDate;
        private LocalDate endDate;
        private List<LocalDate> holidays;
        private List<LocalDate> nonWorkingDays;
        private List<LocalDate> workingDays;

        public Builder startDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder holidays(List<LocalDate> holidays) {
            this.holidays = holidays;
            return this;
        }

        public Builder nonWorkingDays(List<LocalDate> nonWorkingDays) {
            this.nonWorkingDays = nonWorkingDays;
            return this;
        }

        public Builder workingDays(List<LocalDate> workingDays) {
            this.workingDays = workingDays;
            return this;
        }

        public CalendarOutDto build() {
            return new CalendarOutDto(startDate, endDate, holidays, nonWorkingDays, workingDays);
        }
    }
}
