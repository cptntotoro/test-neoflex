package com.example.controller;

import com.example.model.Vacation;
import com.example.service.VacationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestVacationController.class)
public class RestVacationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VacationService vacationService;

    @Test
    public void calculateVacationPay_ValidRequest_ReturnsOk() throws Exception {
        LocalDate startDate = LocalDate.of(2023, 6, 1);
        LocalDate endDate = LocalDate.of(2023, 6, 14);
        double avgAnnualSalary = 180000.0;
        double expectedVacationPay = 7329.38;

        Vacation processedVacation = new Vacation.Builder()
                .startDate(startDate)
                .endDate(endDate)
                .avgAnnualSalary(avgAnnualSalary)
                .amount(expectedVacationPay)
                .build();

        Mockito.when(vacationService.calculateVacationPay(any()))
                .thenReturn(processedVacation);

        mockMvc.perform(MockMvcRequestBuilders.get("/calculate")
                        .param("startDate", "01.06.2023")
                        .param("endDate", "14.06.2023")
                        .param("avgAnnualSalary", "180000.0"))
                .andExpect(status().isOk());
    }

    @Test
    public void calculateVacationPay_InvalidDateFormat_ReturnsBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/calculate")
                        .param("startDate", "01-06-2023") // неправильный формат
                        .param("endDate", "14.06.2023")
                        .param("avgAnnualSalary", "50000.0"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void calculateVacationPay_MissingParameters_ReturnsBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/calculate")
                        .param("startDate", "01.06.2023"))
                .andExpect(status().isBadRequest());
    }
}