package com.jeanlima.olamundo.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;



@RestController
public class OlaMundoController {

    @GetMapping("/saudar")
    public String saudar(){
        return "Olá mundo!";
    }
}
