package com.example.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * Ошибка календаря для REST API
 */
public class RestApiCalendarError {
    private final String status;
    private final String reason;
    private final String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime timestamp;

    private RestApiCalendarError(String status, String reason, String message, LocalDateTime timestamp) {
        this.status = status;
        this.reason = reason;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Построитель {@link RestApiCalendarError}
     * @return Построитель
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String status;
        private String reason;
        private String message;
        private LocalDateTime timestamp;

        /**
         * Установить статус ошибки
         * @param status Статус ошибки
         */
        public Builder status(String status) {
            this.status = status;
            return this;
        }

        /**
         * Установить причину ошибки
         * @param reason Причина ошибки
         */
        public Builder reason(String reason) {
            this.reason = reason;
            return this;
        }

        /**
         * Установить сообщение ошибки
         * @param message Сообщение ошибки
         */
        public Builder message(String message) {
            this.message = message;
            return this;
        }

        /**
         * Установить время ошибки
         * @param timestamp Время ошибки
         */
        public Builder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public RestApiCalendarError build() {
            return new RestApiCalendarError(status, reason, message, timestamp);
        }
    }
}
