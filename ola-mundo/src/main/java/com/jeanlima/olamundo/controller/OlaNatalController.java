package com.jeanlima.olamundo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/olanatal")
public class OlaNatalController {

    @GetMapping("/saudar")
    public String saudar() {
        return "Olá Natal!";

    }
}