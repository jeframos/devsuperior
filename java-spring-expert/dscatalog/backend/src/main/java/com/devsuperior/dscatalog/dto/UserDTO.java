package com.devsuperior.dscatalog.dto;

import com.devsuperior.dscatalog.entities.User;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    private Set<RoleDTO> roles = new HashSet<>();

    public UserDTO() {}

    public UserDTO(Long id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public UserDTO(User entity) {
        id = entity.getId();
        firstName = entity.getFirstName();
        lastName = entity.getLastName();
        email = entity.getEmail();

        //Como foi feita a configuração na classe User.java para a notação @ManyToMany utilizando a
        //configuração "fetch = FetchType.EAGER", os perfis do usuário (roles) já estão disponíveis
        // no momento em que for feita a consulta do usuário, então é possível fazer a consulta dos
        // perfis do usuário (roles) utilizando o método "entity.getRoles()", e para cada perfil do
        // usuário (role) encontrado, é criado um novo RoleDTO e adicionado na coleção de perfis do
        // usuário (roles) presente na classe UserDTO.
        entity.getRoles().forEach(role -> this.roles.add(new RoleDTO(role)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }
}
