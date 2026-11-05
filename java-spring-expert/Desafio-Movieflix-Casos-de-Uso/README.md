# Movieflix - Casos de Uso (DevSuperior)

Este repositório contém o desafio do projeto "Movieflix - Casos de Uso" do curso da DevSuperior (prof. Nélio Alves).

Resumo
-------
- Aplicação backend em Java com Spring Boot que implementa endpoints para filmes, gêneros, usuários e reviews.
- Feito como exercício do curso; inclui testes de integração que exercitam autenticação e autorização.

Tecnologias
----------
- Java 17+
- Spring Boot
- Spring Data JPA
- Spring Security / OAuth2 Resource Server (usado nos testes)
- Maven

Estrutura importante
--------------------
- `src/main/java` - código-fonte da aplicação
- `src/main/resources/application.properties` - configuração da aplicação
- `src/main/resources/import.sql` - script de carga inicial usado nos testes
- `src/test/java` - testes de unidade e integração (ex.: `ReviewResourceIT`)

Como executar
--------------
Pré-requisitos:
- JDK 17 (ou compatível) instalado
- Maven instalado

Rodar a aplicação (modo desenvolvimento):

PowerShell
```
mvn spring-boot:run
```

Rodar todos os testes:

PowerShell
```
mvn test
```

Rodar um teste específico (ex.: teste de integração de Reviews):

PowerShell
```
mvn -Dtest=com.devsuperior.movieflix.controllers.ReviewResourceIT#insertShouldInsertReviewWhenMemberAuthenticatedAndValidData test
```

Endpoints úteis
---------------
POST /reviews
- Descrição: insere uma nova review para um filme.
- Autenticação: requer token Bearer com um usuário com papel MEMBER (nos testes o usuário `ana@gmail.com` é membro).
- Corpo JSON esperado:

```json
{
  "text": "Gostei do filme!",
  "movieId": 1
}
```

Resposta (201 Created) — exemplo parcial:

```json
{
  "id": 10,
  "text": "Gostei do filme!",
  "movieId": 1,
  "userId": 2,
  "userName": "Ana",
  "userEmail": "ana@gmail.com"
}
```

Observações importantes
-----------------------
- Os testes usam dados pré-carregados pelo `import.sql` (veja `src/main/resources/import.sql`). Garanta que os ids referenciados pelos testes existem nesse script.
- A aplicação de exemplo usada no curso obtém o usuário autenticado via `AuthService.authenticated()` — não confiar no `userId` enviado pelo cliente para associar a review.
- Se for necessário validar que um `Movie` existe antes de persistir uma `Review`, use o repositório de `Movie` (por exemplo `movieRepository.findById(...)` ou `getReferenceById(...)`).

Como contribuir / commits
-------------------------
- Use mensagens no estilo Conventional Commits. Exemplo para a correção de NPE feita recentemente:

```
fix(review): prevenir NPE ao inserir review e associar usuário autenticado

Corrige NullPointerException causado por entity.getMovie()/getUser() nulos.
```

Mais informações
----------------
Se você está acompanhando o curso, siga as instruções das aulas do professor Nélio Alves para configurar qualquer serviço externo (por exemplo um Authorization Server local) caso necessário para testes manuais.

---

Arquivo criado para fins de documentação local do projeto.

