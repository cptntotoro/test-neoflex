package com.example.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

/**
 * Класс календаря с автоматическим заполнением рабочих/нерабочих дней
 */
public class Calendar {
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final List<LocalDate> holidays;
    private final List<LocalDate> nonWorkingDays;
    private final List<LocalDate> workingDays;

    public Calendar(LocalDate startDate, LocalDate endDate,
                    Set<LocalDate> customHolidays,
                    Set<LocalDate> customNonWorkingDays) {
        this.startDate = startDate;
        this.endDate = endDate;

        List<LocalDate> holidaysList = new ArrayList<>();
        List<LocalDate> nonWorkingDaysList = new ArrayList<>();
        List<LocalDate> workingDaysList = new ArrayList<>();

        LocalDate current = startDate;
        while (!current.isAfter(endDate)) {
            if (isHoliday(current, customHolidays)) {
                holidaysList.add(current);
            } else if (customNonWorkingDays != null && customNonWorkingDays.contains(current)) {
                nonWorkingDaysList.add(current);
            } else {
                workingDaysList.add(current);
            }
            current = current.plusDays(1);
        }

        this.holidays = Collections.unmodifiableList(holidaysList);
        this.nonWorkingDays = Collections.unmodifiableList(nonWorkingDaysList);
        this.workingDays = Collections.unmodifiableList(workingDaysList);
    }

    private boolean isHoliday(LocalDate date, Set<LocalDate> customHolidays) {
        return (customHolidays != null && customHolidays.contains(date)) ||
                date.getDayOfWeek() == DayOfWeek.SATURDAY ||
                date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
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

    public static class Builder {
        private LocalDate startDate;
        private LocalDate endDate;
        private Set<LocalDate> holidays;
        private Set<LocalDate> nonWorkingDays;

        public Builder startDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder holidays(Set<LocalDate> holidays) {
            this.holidays = holidays;
            return this;
        }

        public Builder nonWorkingDays(Set<LocalDate> nonWorkingDays) {
            this.nonWorkingDays = nonWorkingDays;
            return this;
        }

        public Calendar build() {
            return new Calendar(startDate, endDate, holidays, nonWorkingDays);
        }
    }
}