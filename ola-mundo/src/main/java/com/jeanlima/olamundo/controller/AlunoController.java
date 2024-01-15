package com.jeanlima.olamundo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jeanlima.olamundo.model.Aluno;
import com.jeanlima.olamundo.service.AlunoService;

@RestController
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping("/getAlunoById/{idAluno}")
	 public Aluno getAlunoById(@PathVariable int idAluno){
		return alunoService.getAlunoById(idAluno);
	 } 

    @GetMapping("/getListaAlunos")
    public List<Aluno> getListaAlunos(){
        return alunoService.getListaAlunos();
    }
    
}
