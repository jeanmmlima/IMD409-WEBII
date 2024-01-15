package com.jeanlima.olamundo.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("/olamundo")
public class OlaMundoController {

    @Value("${application.name}")
	private String nomeApp;

    @GetMapping("/saudar")
    public String saudar(){
        return "Olá mundo!";
    }

    @GetMapping("/getNomeAplicacao")
    public String getNomeAplicacao() {
        return nomeApp;
    }
    


}
