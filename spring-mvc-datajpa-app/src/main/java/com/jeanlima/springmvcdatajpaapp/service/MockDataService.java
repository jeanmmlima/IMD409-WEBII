package com.jeanlima.springmvcdatajpaapp.service;

import org.springframework.stereotype.Component;

@Component
public class MockDataService {

    private final String[] cursos = {
        "BTI", "EngSoft", "EngComp", "CienComp"
    };
    private final String[] sistemasOperacionais = {
        "OSX", "Windows", "Linux"
    };
    public String[] getCursos() {
        return cursos;
    }
    public String[] getSistemasOperacionais() {
        return sistemasOperacionais;
    }

    
    
}
