package com.example.validator;

import com.example.exception.IncorrectVacationException;
import com.example.model.Vacation;

public class VacationValidator {

    /**
     * Проверить отпуск
     *
     * @param vacation Отпуск
     */
    public static void validate(Vacation vacation) {
        if (vacation.getStartDate() == null) {
            throw new IncorrectVacationException("Отсутствует дата начала отпуска");
        }

        if (vacation.getEndDate() == null) {
            throw new IncorrectVacationException("Отсутствует дата начала отпуска");
        }

        if (vacation.getAvgAnnualSalary() <= 0) {
            throw new IncorrectVacationException("Средняя зарплата не может быть меньше или равна нулю");
        }

        if (vacation.getStartDate().isAfter(vacation.getEndDate())) {
            throw new IncorrectVacationException("Дата начала отпуска не может быть позже даты окончания отпуска");
        }
    }
}
