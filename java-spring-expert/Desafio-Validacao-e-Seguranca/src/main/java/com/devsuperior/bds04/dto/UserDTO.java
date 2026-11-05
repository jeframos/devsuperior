package com.devsuperior.bds04.dto;

import jakarta.validation.constraints.Email;

public class UserDTO {

        private Long id;

        @Email(message = "Favor informar um email válido!")
        private String email;

        public UserDTO() {}

        public UserDTO(Long id, String email) {
            this.id = id;
            this.email = email;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
}
