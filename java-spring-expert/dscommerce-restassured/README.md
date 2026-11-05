# DSCommerce — prática com Rest Assured

Projeto do curso Java Spring Expert dedicado à prática de testes HTTP com Rest Assured para a API DSCommerce. O `pom.xml` declara Spring Boot 2.7.3, Java 17 e Rest Assured 5.5.1 (incluindo JSONPath e XMLPath no escopo de teste).

## Executar

Na pasta deste projeto:

```powershell
.\mvnw.cmd test
.\mvnw.cmd spring-boot:run
```

A aplicação de entrada está em `src/main/java`. Para verificar quais cenários podem ser executados e se dependem de uma API em execução, consulte `src/test/java` e as configurações em `src/main/resources`.

> Este módulo tem ciclo de vida e dependências próprios. Não presuma que usa a mesma versão ou configuração do projeto DSCommerce em `java-spring-professional/dscommerce`.