package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.entities.User;
import com.devsuperior.dscommerce.services.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    public void validateSelfOrAdmin(long userId){
        User me = userService.authenticated();
        if (me.hasRole("ROLE_ADMIN")) {
            //Como esse método é do tipo void, ao adicionar o return ele vai
            // seguir o fluxo de execução normal do método, ou seja, vai sair do
            //  método e não vai executar o throw new ForbiddenException("Access denied!");
            return;
        }
        if (!me.getId().equals(userId)) {
            throw new ForbiddenException("Access Access denied. Should be self or admin!");
        }
    }
}
