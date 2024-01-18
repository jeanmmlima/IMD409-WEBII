package com.jeanlima.springmvcdatajpaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jeanlima.springmvcdatajpaapp.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno,Integer>{

    
}
