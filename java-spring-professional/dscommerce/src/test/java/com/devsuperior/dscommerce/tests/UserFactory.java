package com.devsuperior.dscommerce.tests;

import com.devsuperior.dscommerce.entities.Role;
import com.devsuperior.dscommerce.entities.User;

import java.time.LocalDate;

public class UserFactory {
    
    public static User createClientUser() {
        User user = new User(
                1L,
                "Maria",
                "maria@gmail.com",
                "98888889",
                LocalDate.parse("2001-07-25"),
                "$2a$10$1vLCsmCjy7zWYtEZk2QI4eOGqTSjoAs2j4Gg0F6FGPt0qKLtfgExS");
        
        user.addRole(new Role(1L, "ROLE_CLIENT"));
        return user;
    }

    public static User createAdminUser() {
        User user = new User(
                2L,
                "Alex",
                "alex@gmail.com",
                "97777777",
                LocalDate.parse("1987-12-13"),
                "$2a$10$1vLCsmCjy7zWYtEZk2QI4eOGqTSjoAs2j4Gg0F6FGPt0qKLtfgExS");

        user.addRole(new Role(1L, "ROLE_ADMIN"));
        return user;
    }

    public static User createCustomClientUser(Long id, String username) {
        User user = new User(
                id,
                "Maria",
                username,
                "98888889",
                LocalDate.parse("2001-07-25"),
                "$2a$10$1vLCsmCjy7zWYtEZk2QI4eOGqTSjoAs2j4Gg0F6FGPt0qKLtfgExS");

        user.addRole(new Role(1L, "ROLE_CLIENT"));
        return user;
    }

    public static User createCustomAdminUser(Long id, String username) {
        User user = new User(
                id,
                "Alex",
                username,
                "97777777",
                LocalDate.parse("1987-12-13"),
                "$2a$10$1vLCsmCjy7zWYtEZk2QI4eOGqTSjoAs2j4Gg0F6FGPt0qKLtfgExS");

        user.addRole(new Role(1L, "ROLE_ADMIN"));
        return user;
    }
    
}
