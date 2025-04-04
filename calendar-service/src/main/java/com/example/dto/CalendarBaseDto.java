package com.example.dto;

import java.time.LocalDate;

public abstract class CalendarBaseDto {
    protected final LocalDate startDate;
    protected final LocalDate endDate;

    protected CalendarBaseDto(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
