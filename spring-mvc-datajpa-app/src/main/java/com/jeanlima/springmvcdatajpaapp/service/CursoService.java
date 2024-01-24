package com.jeanlima.springmvcdatajpaapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jeanlima.springmvcdatajpaapp.model.Curso;
import com.jeanlima.springmvcdatajpaapp.repository.CursoRepository;

@Component
public class CursoService {

    @Autowired
    CursoRepository cursoRepository;

    public List<Curso> getCursos(){
        return cursoRepository.findAll();
    }

    public Curso getCursoById(Integer id){
        return cursoRepository.findById(id).map(curso -> {
            return curso;
        }).orElseThrow(() -> null);
    }
    
}
