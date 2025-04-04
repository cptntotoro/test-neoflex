package com.example.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

/**
 * Класс исходящего DTO календаря
 */
public class CalendarOutDto {
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    protected LocalDate startDate;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    protected LocalDate endDate;
    private List<LocalDate> holidays;
    private List<LocalDate> nonWorkingDays;
    private List<LocalDate> workingDays;

    public CalendarOutDto() {
    }

    @JsonCreator
    private CalendarOutDto(@JsonProperty("startDate") LocalDate startDate,
                           @JsonProperty("endDate") LocalDate endDate,
                           @JsonProperty("holidays") List<LocalDate> holidays,
                           @JsonProperty("nonWorkingDays") List<LocalDate> nonWorkingDays,
                           @JsonProperty("workingDays") List<LocalDate> workingDays) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.holidays = holidays;
        this.nonWorkingDays = nonWorkingDays;
        this.workingDays = workingDays;
    }

    /**
     * Получить дату начала отпуска
     * @return Дата начала отпуска
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Получить дату окончания отпуска
     * @return Дата начала отпуска
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Получить список праздничных дат
     * @return Список праздничных дат
     */
    public List<LocalDate> getHolidays() {
        return holidays;
    }

    /**
     * Получить список нерабочих дат
     * @return Список нерабочих дат
     */
    public List<LocalDate> getNonWorkingDays() {
        return nonWorkingDays;
    }

    /**
     * Получить список рабочих дат
     * @return Список рабочих дат
     */
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

        /**
         * Установить дату начала отпуска
         * @param startDate Дата начала отпуска
         */
        public Builder startDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        /**
         * Установить дату окончания отпуска
         * @param endDate Дата начала отпуска
         */
        public Builder endDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        /**
         * Установить список праздничных дат
         * @param holidays Список праздничных дат
         */
        public Builder holidays(List<LocalDate> holidays) {
            this.holidays = holidays;
            return this;
        }

        /**
         * Установить список нерабочих дат
         * @param nonWorkingDays Список нерабочих дат
         */
        public Builder nonWorkingDays(List<LocalDate> nonWorkingDays) {
            this.nonWorkingDays = nonWorkingDays;
            return this;
        }

        /**
         * Установить список рабочих дат
         * @param workingDays Список рабочих дат
         */
        public Builder workingDays(List<LocalDate> workingDays) {
            this.workingDays = workingDays;
            return this;
        }

        public CalendarOutDto build() {
            return new CalendarOutDto(startDate, endDate, holidays, nonWorkingDays, workingDays);
        }
    }
}
