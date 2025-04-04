package com.example.dto;

import java.time.LocalDate;

/**
 * Класс входящего DTO календаря
 */
public class CalendarInDto extends CalendarBaseDto {
    private CalendarInDto(LocalDate startDate, LocalDate endDate) {
        super(startDate, endDate);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private LocalDate startDate;
        private LocalDate endDate;

        public Builder() {
        }

        public Builder startDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public CalendarInDto build() {
            return new CalendarInDto(startDate, endDate);
        }
    }
}
