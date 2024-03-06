package com.jeanlima.springrestapiapp.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jeanlima.springrestapiapp.service.impl.UsuarioServiceImpl;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//intercepta a requisição
public class JwtAuthFilter extends OncePerRequestFilter {

    private JwtService jwtService;
    private UsuarioServiceImpl usuarioService;

    public JwtAuthFilter(JwtService jwtService, UsuarioServiceImpl usuarioService) {
        this.jwtService = jwtService;
        this.usuarioService = usuarioService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {

        String authorization = httpServletRequest.getHeader("Authorization");

        if (authorization != null && authorization.startsWith("Bearer")) {

            String token = authorization.split(" ")[1];
            boolean isValid = jwtService.tokenValido(token);

            if (isValid) {
                // Obtenção do login do usuário a partir do token JWT
                String loginUsuario = jwtService.obterLoginUsuario(token);
                // Carregamento dos detalhes do usuário a partir do login (a partir do BD)
                UserDetails usuario = usuarioService.loadUserByUsername(loginUsuario);
                // Criação de uma instância de UsernamePasswordAuthenticationToken para
                // autenticar o usuário
                UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(usuario, null,
                        usuario.getAuthorities());
                /*
                 * detalhes da autenticação para o objeto user com base nos detalhes da solicitação HTTP 
                 * fornecidos pela instância de HttpServletRequest. 
                 * Isso é útil para registrar informações adicionais sobre a autenticação, 
                 * especialmente em um contexto de aplicativo da web - ip do cliente, agente do usário...
                 */
                user.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                // Configuração da autenticação no contexto de segurança do Spring
                /*
                 * está configurando a instância de autenticação user como a instância 
                 * atualmente autenticada no contexto de segurança. 
                 * Isso significa que o usuário associado a essa instância de autenticação 
                 * será considerado o usuário autenticado para a duração da solicitação ou da sessão,
                 *  dependendo da configuração.
                 */
                SecurityContextHolder.getContext().setAuthentication(user);
            }
        }

        // passa a requisição com o usuário autenticado no contexto de segurança
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }

}
