package com.practice.calculator_web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String Home() {

        String home = "home";
        return home;
    }
}
