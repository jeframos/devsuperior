package com.devsuperior.dscatalog.resources;

import com.devsuperior.dscatalog.dto.EmailDTO;
import com.devsuperior.dscatalog.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
public class AuthResource {

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/recover-token")
    public ResponseEntity<Void> createRecoveryToken(@Valid @RequestBody EmailDTO body) {
         authService.createRecoveryToken(body);
        return ResponseEntity.noContent().build();
    }
}