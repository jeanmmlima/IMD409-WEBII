package com.jeanlima.springmvcdatajpaapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeanlima.springmvcdatajpaapp.model.Curso;



public interface CursoRepository extends JpaRepository<Curso,Integer>{
    
}
