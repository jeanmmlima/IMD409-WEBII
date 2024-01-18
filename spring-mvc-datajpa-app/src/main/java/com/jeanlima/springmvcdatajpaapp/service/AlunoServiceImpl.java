package com.jeanlima.springmvcdatajpaapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jeanlima.springmvcdatajpaapp.model.Aluno;
import com.jeanlima.springmvcdatajpaapp.repository.AlunoRepository;



@Component
public class AlunoServiceImpl implements  AlunoService{

    @Autowired
    AlunoRepository alunoRepository;
 

    @Override
    public void salvarAluno(Aluno aluno) {
        alunoRepository.salvar(aluno);
        
    }

    @Override
    public void deletarAluno(Aluno aluno) {
        alunoRepository.deletar(aluno);
    }

    @Override
    public Aluno getAlunoById(Integer id) {
        return alunoRepository.obterPorId(id);
    }

    @Override
    public List<Aluno> getListaAluno() {
        return alunoRepository.obterTodos();
    }

    
}
