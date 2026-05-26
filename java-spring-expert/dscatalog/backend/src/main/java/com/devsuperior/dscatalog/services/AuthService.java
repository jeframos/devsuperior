package com.devsuperior.dscatalog.services;

import com.devsuperior.dscatalog.dto.EmailDTO;
import com.devsuperior.dscatalog.entities.PasswordRecover;
import com.devsuperior.dscatalog.entities.User;
import com.devsuperior.dscatalog.repositories.PasswordRecoverRepository;
import com.devsuperior.dscatalog.repositories.UserRepository;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class AuthService {

    @Value("${email.password-recover.token.minutes}")
    private Long tokenMinutes;

    @Value("${email.password-recover.uri}")
    private String recoverUri;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordRecoverRepository passwordRecoverRepository;

    public void createRecoveryToken(EmailDTO body) {

        //1° Passo: Verificar se o email existe no banco de dados
        User user = userRepository.findByEmail(body.getEmail());
        if (user == null) {
            throw new ResourceNotFoundException("Email não encontrado");
        }

        //2° Passo: Gerar um token com validade de X minutos, e salvar isso no banco de dados.
        String token = UUID.randomUUID().toString();

        PasswordRecover entity = new PasswordRecover();
        entity.setEmail(body.getEmail());
        entity.setToken(token);
        entity.setExpiration(Instant.now().plusSeconds(tokenMinutes * 60L));
        passwordRecoverRepository.save(entity);

        //3° Passo: Enviar um email para o usuário com um link para usar no token
        String msgBody = "Acesse o link para definir uma nova senha\n\n"
                + recoverUri + token + ". Validade de " + tokenMinutes + " minutos";

        emailService.sendEmail(body.getEmail(), "Recuperação de senha", msgBody);
    }
}
