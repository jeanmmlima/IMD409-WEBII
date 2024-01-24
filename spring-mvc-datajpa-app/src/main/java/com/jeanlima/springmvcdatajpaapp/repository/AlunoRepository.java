package com.jeanlima.springmvcdatajpaapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jeanlima.springmvcdatajpaapp.model.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno,Integer>{

    /*
     * Trabalhando com @Query
     */
    //hql
    @Query(value = " select c from Aluno c where c.nome like %:nome% ")
    List<Aluno> encontrarPorNome(@Param("nome") String nome);

    //sql nativo
    @Query(value = " select * from aluno c where c.nome like %:nome% ",nativeQuery = true)
    List<Aluno> encontrarPorNomeMod(@Param("nome") String nome);

    @Query(value = " delete from Aluno c where c.nome =:nome")
    @Modifying //pois não é só consulta - transactional 
    void deletarPorNome(String nome);

    @Query(value = " select e.* from aluno e where e.curso_id = ?1",nativeQuery = true)
    List<Aluno> findAllByIdCurso(Integer id);

    @Query(value="SELECT DISTINCT e FROM Aluno e JOIN FETCH e.disciplinas")
    List<Aluno> findAllFetchDisciplinas();

    @Query(value="SELECT e FROM Aluno e JOIN e.disciplinas d where d.id =:id")
    List<Aluno> findAllByDisciplinaId(@Param("id") Integer id);
}
