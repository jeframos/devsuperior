# Desafio Consulta de Vendas — DSMeta

API REST de consulta e resumo de vendas, do curso Java Spring Professional. O projeto usa Spring Boot 2.7.3, Java 17, Spring Data JPA e H2, conforme declarado em `pom.xml`.

## Endpoints

- `GET /sales/{id}`: consulta uma venda pelo identificador.
- `GET /sales/report`: relatório paginado; aceita `minDate`, `maxDate` e `name` como filtros opcionais.
- `GET /sales/summary`: resumo de vendas; aceita `minDate` e `maxDate` opcionais.

## Executar e testar

Na pasta do projeto:

```powershell
.\mvnw.cmd spring-boot:run
.\mvnw.cmd test
```

A configuração do banco de dados e do perfil de execução está em `src/main/resources`. Consulte-a antes de trocar o banco ou usar dados persistentes.