# DSList — backend

Backend do DSList, aplicação para organizar jogos em listas e alterar sua posição. O projeto pertence ao material DevSuperior consolidado na pasta `devsuperior-ci-cd`; este diretório contém a aplicação Maven.

## Executar e testar

Na pasta `java-spring-professional/devsuperior-ci-cd/dslist-backend`:

```powershell
.\mvnw.cmd spring-boot:run
.\mvnw.cmd test
```

A aplicação usa perfis definidos em `src/main/resources/application.properties`. O perfil de teste usa H2; as variáveis de ambiente e origens CORS podem ser configuradas conforme as propriedades documentadas nesse arquivo.

As entidades principais são jogos, listas de jogos e a associação que guarda a posição do jogo na lista. O projeto também inclui consultas e atualização de posição no repositório. Consulte os controllers e as configurações locais para os detalhes das rotas e do perfil ativo.