# DSLearn

Aplicação de referência do curso Java Spring Reference. O projeto apresenta uma API de aprendizagem digital e persistência com Spring Data JPA. O `pom.xml` declara Spring Boot 4.1.2, Java 21 e H2 para execução local; consulte o arquivo antes de escolher o JDK.

## Executar e testar

Na pasta `java-spring-reference/dslearn`:

```powershell
.\mvnw.cmd spring-boot:run
.\mvnw.cmd test
```

A classe de entrada é `DslearnApplication`. As configurações ficam em `src/main/resources/application.properties`, e os dados iniciais estão em `src/main/resources/data.sql`.

O `HELP.md` nessa pasta contém links genéricos do Spring Initializr; este README descreve o projeto que está no repositório.