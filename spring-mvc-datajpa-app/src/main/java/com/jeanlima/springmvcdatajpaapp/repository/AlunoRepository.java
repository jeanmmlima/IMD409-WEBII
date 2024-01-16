package com.jeanlima.springmvcdatajpaapp.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jeanlima.springmvcdatajpaapp.model.Aluno;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class AlunoRepository {

    //utilizando entity manager!
    @Autowired
    private EntityManager entityManager;


    //preciso anotar com transactional para dizer que vai fazer uma transação com o banco (commit and finish)
    @Transactional
    public Aluno salvar(Aluno aluno){
        entityManager.persist(aluno);
        return aluno;
    }

    @Transactional
    public Aluno atualizar(Aluno aluno){
        entityManager.merge(aluno);
        return aluno;
    }

    @Transactional
    public void deletar(Aluno aluno){
        if(!entityManager.contains(aluno)){
            aluno = entityManager.merge(aluno);
        }
        entityManager.remove(aluno);
    }

    @Transactional
    public void deletaById(Integer id){
        Aluno aluno = entityManager.find(Aluno.class, id);
        deletar(aluno);
    }


    @Transactional(readOnly = true)
    public List<Aluno> buscarPorNome(String nome){
        //:nome serve para definir o parametro jpa
        String jpql = " select c from Aluno c where c.nome like :nome";
        TypedQuery<Aluno> query = entityManager.createQuery(jpql,Aluno.class);
        query.setParameter("nome", "%"+nome+"%");
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    public List<Aluno> obterTodos(){
        return entityManager
        .createQuery("from Aluno",Aluno.class)
        .getResultList();
        
    }

    @Transactional(readOnly = true)
    public Aluno obterPorId(Integer id){
        return entityManager.find(Aluno.class, id);
    }
    
}
