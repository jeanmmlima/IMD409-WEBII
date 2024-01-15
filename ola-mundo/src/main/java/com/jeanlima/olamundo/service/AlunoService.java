package com.jeanlima.olamundo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jeanlima.olamundo.model.Aluno;

@Service
public class AlunoService {

    private List<Aluno> listaAlunos = new ArrayList<Aluno>();

    AlunoService(){
        preencheListaAlunos();
    }

    public void preencheListaAlunos(){
        listaAlunos.add(new Aluno(1, "João", "BTI"));
        listaAlunos.add(new Aluno(2, "Maria", "BTI"));
        listaAlunos.add(new Aluno(3, "José", "Engenharia de Software"));
    }

    public List<Aluno> getListaAlunos(){
        return listaAlunos;

    }

    public Aluno getAlunoById(Integer id){
        return listaAlunos.get(id-1);
    }
    
}
