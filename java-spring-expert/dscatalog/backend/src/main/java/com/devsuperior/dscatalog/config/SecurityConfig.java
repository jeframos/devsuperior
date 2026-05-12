package com.devsuperior.dscatalog.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


//Essa classe foi criada de forma temporária para exemplificar o uso do banco de dados, e não tem
//relação com a segurança. Ela pode ser apagada depois.
//
//Foram implementadas as classes 'AuthorizationServerConfig' e 'ResourceServerConfig' para implementar
//a parte de segurança na aplicação.

/*
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable());
        http.securityMatcher(PathRequest.toH2Console()).csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));
        http.authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        return http.build();
    }
}

 */