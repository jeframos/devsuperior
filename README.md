# Workspace DevSuperior — Prof. Nélio Alves

Este repositório reúne aplicações, desafios e materiais de apoio desenvolvidos nos cursos da DevSuperior. Os projetos estão separados por curso; cada aplicação tem seu próprio `pom.xml` e pode exigir uma versão específica do Java.

## Cursos e aplicações

### Java Spring Expert

- [DSCatalog — backend](java-spring-expert/dscatalog/backend/README.md): catálogo de produtos com Spring Data JPA.
- [DSCommerce — exercício de testes com Rest Assured](java-spring-expert/dscommerce-restassured/README.md).
- [DSMovie — testes de serviços e cobertura JaCoCo](java-spring-expert/desafio-dsmovie-jacoco/README.md).
- [DSMovie — desafio Rest Assured](java-spring-expert/desafio-dsmovie-restassured/README.md): inclui projeto-base e módulo de prática com Rest Assured.
- [Movieflix — casos de uso](java-spring-expert/Desafio-Movieflix-Casos-de-Uso/README.md).
- [Desafio de validação e segurança](java-spring-expert/Desafio-Validacao-e-Seguranca/README.md): API de cidades e eventos com validação e segurança.
- [PostgreSQL e pgAdmin com Docker Compose](java-spring-expert/devsuperior-expert-postgres-pgAdmin/README.md): infraestrutura de apoio ao curso.

### Java Spring Professional

- [BDS Consultas](java-spring-professional/bdsconsultas-main/README.md): seis exercícios SQL (URI 2602, 2609, 2611, 2621, 2737 e 2990).
- [Desafio de componentes e injeção de dependência](java-spring-professional/desafio-componentes-injecao-dependencia/README.md).
- [Desafio Consulta de Vendas (DSMeta)](java-spring-professional/desafio-consulta-vendas/README.md).
- [DSList — backend e material do módulo CI/CD](java-spring-professional/devsuperior-ci-cd/dslist-backend/README.md).
- [DSCommerce](java-spring-professional/dscommerce/README.md).
- [DSCrud](java-spring-professional/dscrud/README.md).
- [DSEvent](java-spring-professional/dsevent/README.md).
- [Spring Boot OAuth2/JWT](java-spring-professional/spring-boot-oauth2-jwt-demo/password-grant/README.md): exemplos de authorization server, resource server e versões distintas do Spring Boot.

### Java Spring Reference

- [DSLearn](java-spring-reference/dslearn/README.md): aplicação de referência para gestão de aprendizagem.

## Executar um projeto

Entre na pasta do projeto desejado e consulte seu `pom.xml` e README para conferir os requisitos e configurações. A maioria dos projetos disponibiliza o Maven Wrapper:

```powershell
cd caminho\para\o\projeto
.\mvnw.cmd spring-boot:run
```

Para rodar os testes, use `.\mvnw.cmd test`. Em sistemas Unix, use `./mvnw spring-boot:run` e `./mvnw test`. Projetos sem wrapper podem ser executados com Maven instalado (`mvn spring-boot:run` / `mvn test`).

> Os projetos têm versões diferentes de Java, Spring Boot e bancos de dados. Use a versão indicada no `pom.xml` e no README do projeto; não há uma configuração única para toda a workspace.

Para a relação organizada dos documentos, consulte o [índice de READMEs](INDICE_READMES.md). As orientações para manter a documentação ficam em [GUIA_MANUTENCAO.md](GUIA_MANUTENCAO.md).