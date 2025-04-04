package com.example.service;

import com.example.model.Vacation;

/**
 * Сервис управления отпусками
 */
public interface VacationService {

    /**
     * Рассчитать отпускные выплаты
     * @param vacation Отпуск
     */
    Vacation calculateVacationPay(Vacation vacation);
}
