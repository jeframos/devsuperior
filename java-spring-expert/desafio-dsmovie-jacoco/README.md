# DSMovie — testes e cobertura com JaCoCo

Aplicação do desafio DSMovie usada para exercitar testes de unidade dos serviços e geração de relatório de cobertura. A configuração atual declara Spring Boot 4.0.6, Java 25, Spring Data JPA, H2, validação e Spring Security/OAuth2; confirme o JDK no `pom.xml` antes de executar.

## Executar e testar

Na pasta deste projeto:

```powershell
.\mvnw.cmd test
.\mvnw.cmd clean package
```

O JaCoCo está configurado no Maven. Após `package`, o relatório HTML fica em `target/jacoco-report/index.html`. O plugin exclui da análise de cobertura, entre outros, controllers, entidades, DTOs e configurações; confira os filtros no `pom.xml` ao interpretar os percentuais.

O projeto contém coleções e ambiente Postman em `docs-curso/`. As configurações de perfis e a origem de dados estão em `src/main/resources/application.properties` e nos arquivos de configuração de teste.

> Use o Wrapper do projeto ou uma instalação de Maven compatível. As versões acima são as declaradas atualmente no `pom.xml` e podem mudar.