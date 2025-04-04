package com.example.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * Ошибка отпуска для REST API
 */
public class VacationError {
    private final String status;
    private final String reason;
    private final String message;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime timestamp;

    private VacationError(String status, String reason, String message, LocalDateTime timestamp) {
        this.status = status;
        this.reason = reason;
        this.message = message;
        this.timestamp = timestamp;
    }

    /**
     * Получить статус ошибки
     * @return Статус ошибки
     */
    public String getStatus() {
        return status;
    }

    /**
     * Получить причину ошибки
     * @return Причина ошибки
     */
    public String getReason() {
        return reason;
    }

    /**
     * Получить сообщение ошибки
     * @return Сообщение ошибки
     */
    public String getMessage() {
        return message;
    }

    /**
     * Получить время ошибки
     * @return Время ошибки
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Построитель {@link VacationError}
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

        public VacationError build() {
            return new VacationError(status, reason, message, timestamp);
        }
    }
}
