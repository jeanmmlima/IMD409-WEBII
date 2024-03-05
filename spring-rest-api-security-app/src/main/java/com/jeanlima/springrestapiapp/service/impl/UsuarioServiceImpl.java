package com.jeanlima.springrestapiapp.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.jeanlima.springrestapiapp.exception.SenhaInvalidaException;
import com.jeanlima.springrestapiapp.model.Usuario;
import com.jeanlima.springrestapiapp.repository.UsuarioRepository;

import jakarta.transaction.Transactional;



/*
 * interface do spring security serve para definir o carregam, UserDetailsento de usuários através de uma base de dados
 */
@Component
public class UsuarioServiceImpl implements UserDetailsService {

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository repository;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }

    public UserDetails autenticar( Usuario usuario ){
        UserDetails user = loadUserByUsername(usuario.getLogin());
        boolean senhasBatem = passwordEncoder.matches( usuario.getSenha(), user.getPassword() );

        if(senhasBatem){
            return user;
        }

        throw new SenhaInvalidaException();
    }

    /*
     * Ele é responsável por carregar os detalhes do usuário com base no nome de usuário fornecido.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

         // Consulta ao repositório para obter o usuário com base no nome de usuário fornecido
        Usuario usuario = repository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados."));

        String[] roles = usuario.isAdmin() ? new String[] { "ADMIN", "USER" } : new String[] { "USER" };

        // Cria e retorna o objeto UserDetails com os detalhes do usuário
        return User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();

    }

}
