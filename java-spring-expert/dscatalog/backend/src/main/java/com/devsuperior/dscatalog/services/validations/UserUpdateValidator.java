package com.devsuperior.dscatalog.services.validations;

import com.devsuperior.dscatalog.dto.UserInsertDTO;
import com.devsuperior.dscatalog.dto.UserUpdateDTO;
import com.devsuperior.dscatalog.entities.User;
import com.devsuperior.dscatalog.repositories.UserRepository;
import com.devsuperior.dscatalog.resources.exceptions.FieldMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserUpdateValidator implements ConstraintValidator<UserUpdateValid, UserUpdateDTO> {

    //Este objeto 'HttpServletRequest' guarda as informações da requisição feita.
    //Ou seja, ele tem acesso a todos os dados da requisição, como por exemplo, os parâmetros,
    //os cabeçalhos, o corpo da requisição, etc.
    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserRepository repository;

    @Override
    public void initialize(UserUpdateValid ann) {
    }

    //Esse método 'isValid' está testando se o objeto 'UserUpdateDTO' é válido, ou seja,
    //se ele atende as regras de validação que foram definidas.
    //Esse objeto é booleano, ou seja. Ele retorna 'true' se o objeto é válido, e 'false'
    //se contém ao menos um erro.
    @Override
    public boolean isValid(UserUpdateDTO dto, ConstraintValidatorContext context) {

        //Essa função captura o MAPPING com os atributos da URI, ou seja, ele captura o ID
        // do usuário que está sendo atualizado.
        //O ID do usuário é necessário para verificar se o email informado no 'dto' já existe
        // no banco de dados, e se o ID do usuário encontrado for diferente do ID do usuário que
        // está sendo atualizado, então é considerado um erro, pois o email já existe para outro
        // usuário.
        @SuppressWarnings("unchecked")
        //Foi necessário efetuar esse casting '(Map<String, String>)' para converter o objeto
        // retornado pelo método 'getAttribute' para o tipo 'Map<String, String>'.
        // Pois tudo que passamos na requisição HTTP é String tanto chave quanto valor.
        var uriVars = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

        //Foi necessário efetuar o parsing 'Long.parseLong' para converter o ID do usuário,
        //que é uma String, para um tipo Long, pois o ID do usuário é do tipo Long no banco de dados.
        Long userId = Long.parseLong(uriVars.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        //Esse if vai verificar se o email informado no 'dto' já existe no banco de dados.
        //Caso exista, a msg da lista de erros 'list' irá receber um novo objeto do
        //tipo 'FieldMessage' com o nome do campo e a mensagem de erro.
        User user = repository.findByEmail(dto.getEmail());
        if (user != null && userId != user.getId()) {
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
