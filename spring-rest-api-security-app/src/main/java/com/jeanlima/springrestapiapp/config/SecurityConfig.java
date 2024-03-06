package com.jeanlima.springrestapiapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.jeanlima.springrestapiapp.security.JwtAuthFilter;
import com.jeanlima.springrestapiapp.security.JwtService;
import com.jeanlima.springrestapiapp.service.impl.UsuarioServiceImpl;


@Configuration
@EnableWebSecurity //configuracao de segurança
public class SecurityConfig {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioServiceImpl usuarioService;


    @Bean
    public OncePerRequestFilter jwtFilter(){
        return new JwtAuthFilter(jwtService, usuarioService);
    }
    /*
     * SecurityFilterChain, que é uma cadeia de filtros de segurança do Spring Security. 
     * Ele recebe um objeto HttpSecurity como parâmetro, que é usado para configurar a 
     * segurança HTTP
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) ->                                     
            authz
                .requestMatchers(HttpMethod.POST, "/api/usuarios/**")
                    .permitAll()
                .requestMatchers("/api/clientes/**")
                    .hasAnyRole("USER","ADMIN")
                .requestMatchers("/api/produtos/**")
                    .hasRole("ADMIN")
                .requestMatchers("/api/pedidos/**") 
                    .hasAnyRole("USER","ADMIN") 
                .anyRequest().authenticated()
                
            )
            .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)

            //habilitado por padrão
            .csrf(AbstractHttpConfigurer::disable);
            //.httpBasic(Customizer.withDefaults()); //possibilita "logar" com o headers de autenticação
         //retorno da cadeia de filtros   
        return http.build();
        
    }




    
}
