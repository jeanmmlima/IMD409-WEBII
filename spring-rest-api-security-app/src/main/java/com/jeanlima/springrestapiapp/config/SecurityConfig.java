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
                .requestMatchers(
                    "/v3/api-docs/**", 
                "/swagger-resources/**", 
                "/swagger-ui/**")
                    .permitAll()
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
            /*sessionManagement
             * é um método usado para configurar a política de criação de sessão 
             * uma expressão lambda é usada para configurar a política de criação de sessão como 
             * STATELESS (sem estado). 
             * Isso significa que as sessões não serão criadas pelo framework e 
             * cada solicitação será tratada independentemente, sem depender de estado de sessão. 
             * Isso é frequentemente usado em APIs RESTful ou aplicativos da web sem estado, 
             * onde não é necessário manter o estado da sessão.
             */
            .sessionManagement((session) -> 
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            
            /*
             * No Java Spring Security, o método addFilterBefore() é usado para adicionar um filtro 
             * antes de um filtro específico na cadeia de filtros de segurança. 
             * Isso significa que o filtro JWT será executado antes do filtro de autenticação 
             * de nome de usuário e senha. 
             * O filtro JWT é usado para validar tokens JWT em solicitações HTTP para 
             * autenticação e autorização, 
             * Adicionando o filtro JWT antes do UsernamePasswordAuthenticationFilter, 
             * você está configurando o sistema para primeiro verificar se há um token JWT válido antes de 
             * tentar autenticar com nome de usuário e senha. Isso é comum em aplicativos que usam autenticação baseada em tokens JWT.
             */
            .addFilterBefore(
                jwtFilter(), 
                UsernamePasswordAuthenticationFilter.class)

            //habilitado por padrão
            .csrf(AbstractHttpConfigurer::disable);
            //.httpBasic(Customizer.withDefaults()); //possibilita "logar" com o headers de autenticação
         //retorno da cadeia de filtros   
        return http.build();
        
    }




    
}
