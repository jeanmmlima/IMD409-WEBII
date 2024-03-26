package com.jeanlima.springrestapiapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jeanlima.springrestapiapp.model.Usuario;



public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByLogin(String login);
}
