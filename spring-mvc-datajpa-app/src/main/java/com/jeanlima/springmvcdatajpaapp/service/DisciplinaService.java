package com.jeanlima.springmvcdatajpaapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import com.jeanlima.springmvcdatajpaapp.model.Disciplina;
import com.jeanlima.springmvcdatajpaapp.repository.DisciplinaRepository;

@Component
public class DisciplinaService {
    
    @Autowired
    private DisciplinaRepository disciplinaRepository;

     public List<Disciplina> getDisciplinas(){
        return disciplinaRepository.findAll();
    }

    public Disciplina getDisciplinaById(Integer id){
        return disciplinaRepository.findById(id).map(disciplina -> {
            return disciplina;
        }).orElseThrow(() -> null);
    }

    public List<Disciplina> getAllDisciplinasByAlunoID(Integer id) {
        return disciplinaRepository.findAllByAlunoId(id);
    }

    public List<Disciplina> getDisciplinasByIds(List<Integer> ids){
        return disciplinaRepository.findAllById(ids);
    }
}
