package com.devsuperior.dscatalog.services.validations;

import com.devsuperior.dscatalog.dto.UserInsertDTO;
import com.devsuperior.dscatalog.entities.User;
import com.devsuperior.dscatalog.repositories.UserRepository;
import com.devsuperior.dscatalog.resources.exceptions.FieldMessage;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {

    @Autowired
    private UserRepository repository;

    @Override
    public void initialize(UserInsertValid ann) {
    }

    //Esse método 'isValid' está testando se o objeto 'UserInsertDTO' é válido, ou seja,
    //se ele atende as regras de validação que foram definidas.
    //Esse objeto é booleano, ou seja. Ele retorna 'true' se o objeto é válido, e 'false'
    //se contém ao menos um erro.
    @Override
    public boolean isValid(UserInsertDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        //Esse if vai verificar se o email informado no 'dto' já existe no banco de dados.
        //Caso exista, a msg da lista de erros 'list' irá receber um novo objeto do
        //tipo 'FieldMessage' com o nome do campo e a mensagem de erro.
        User user = repository.findByEmail(dto.getEmail());
        if (user != null) {
            list.add(new FieldMessage("email", "Email já existe"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFiledName())
                    .addConstraintViolation();
        }

        //O retorno 'list.isEmpty()' verifica se a lista estiver vazia indica que não apresentou nenhum erro.
        //Logo será retornado true ou false dependendo se a lista estiver vazia.
        return list.isEmpty();
    }
}
