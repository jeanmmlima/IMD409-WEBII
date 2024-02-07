package com.jeanlima.springrestapiapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity //configuracao de segurança
public class SecurityConfig {

    //interface para codificação de senhas
    @Bean
    public PasswordEncoder passwordEncoder(){
            //usa o algoritmo de hash BCrypt para codificar senhas de forma segura
            return new BCryptPasswordEncoder();
    };

    /*
     * Implementação não persistente do UserDetailsManager que é apoiada por um mapa em memória.
       Principalmente destinado a fins de teste e demonstração, onde um sistema persistente 
       completo não é necessário.
     */
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        /*
         *  É usada para encapsular os detalhes necessários sobre o usuário 
         * durante o processo de autenticação e autorização.
         */
        UserDetails user = User
            .withUsername("fulano")
            .password(passwordEncoder().encode("123"))
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    };

    /*
     * SecurityFilterChain, que é uma cadeia de filtros de segurança do Spring Security. 
     * Ele recebe um objeto HttpSecurity como parâmetro, que é usado para configurar a 
     * segurança HTTP
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/api/clientes/**").authenticated() //precisa estar autenticado
                .requestMatchers("/api/produtos/**").permitAll() //vai permitir todos
                .requestMatchers("/api/pedidos/**").hasRole("ADMIN") //vai permitar com papel ADMIN
            )
            .formLogin(Customizer.withDefaults());
         //retorno da cadeia de filtros   
        return http.build();
    }




    
}
