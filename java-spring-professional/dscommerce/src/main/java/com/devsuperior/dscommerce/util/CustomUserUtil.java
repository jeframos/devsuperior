package com.devsuperior.dscommerce.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class CustomUserUtil {

    public String getLoggedUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //Quando a linha de cima não consegue obter o usuário, o Spring Security retorna um objeto de
        // autenticação do tipo AnonymousAuthenticationToken, que não possui informações do usuário logado.
        //Então ao tentar efetuar o CAST na linha de baixo o compilador gera a exceção "ClassCastException"
        // devido não não possuir dado do usuário.
        Jwt jwtPrincipal = (Jwt) authentication.getPrincipal();
        return jwtPrincipal.getClaim("username");
    }
}
