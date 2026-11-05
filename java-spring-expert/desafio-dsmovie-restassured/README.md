# DSMovie — desafio Rest Assured

Esta pasta contém dois módulos Maven relacionados ao desafio de testes da API DSMovie:

- [`projeto-base`](projeto-base/): aplicação-base DSMovie, com código-fonte e testes próprios.
- [`dsmovie-restassured`](dsmovie-restassured/): módulo de prática que declara Rest Assured e Groovy no escopo de teste.

A coleção e o ambiente Postman estão nesta pasta (`Desafio DSMovie.postman_collection.json` e `DSMovie.postman_environment.json`). Cada módulo tem seu próprio `pom.xml` e Maven Wrapper; rode os comandos dentro do diretório correspondente.

## Executar os módulos

Para iniciar a aplicação-base:

```powershell
cd projeto-base
.\mvnw.cmd spring-boot:run
```

Para executar os testes de um módulo:

```powershell
cd dsmovie-restassured
.\mvnw.cmd test
```

Consulte cada `pom.xml` para confirmar a versão de Java e dependências. No estado atual, os POMs declaram Spring Boot 4.0.6 e Java 25. O projeto-base inclui configuração com H2, validação e segurança; o módulo de prática declara Rest Assured 5.5.0 e Groovy 4.0.28 para testes.