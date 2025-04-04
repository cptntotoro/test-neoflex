package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewVacationController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Hello from Thymeleaf!");
        model.addAttribute("currentYear", java.time.Year.now().getValue());
        return "index";
    }
}
