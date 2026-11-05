# Guia de manutenção da documentação

Use este guia ao incluir, alterar ou remover aplicações neste workspace. A documentação deve refletir as pastas e configurações que realmente existem, sem pressupor uma versão comum de Java ou Spring Boot.

## Ao adicionar uma aplicação

1. Inclua um README no diretório do projeto com propósito, pré-requisitos, execução, testes e observações específicas.
2. Consulte o `pom.xml` e os arquivos de configuração para registrar versões e dependências corretamente.
3. Atualize os cursos e links em `README.md` e `INDICE_READMES.md`.
4. Atualize `RESUMO_FINAL.md` se o inventário de cursos ou projetos mudar.
5. Confira se os links relativos apontam para arquivos existentes.

## Ao alterar um projeto

- Revise comandos de execução e teste após mudanças no build.
- Atualize rotas, configuração de banco, autenticação e exemplos quando forem afetados.
- Não copie credenciais reais para a documentação; use os valores de desenvolvimento somente quando forem necessários para reproduzir o projeto.
- Separe informações específicas de um módulo das informações gerais da workspace.

## Estrutura atual

```text
devsuperior/
├── README.md
├── INDICE_READMES.md
├── GUIA_MANUTENCAO.md
├── RESUMO_FINAL.md
├── java-spring-expert/
│   ├── desafio-dsmovie-jacoco/
│   ├── desafio-dsmovie-restassured/
│   ├── Desafio-Movieflix-Casos-de-Uso/
│   ├── Desafio-Validacao-e-Seguranca/
│   ├── devsuperior-expert-postgres-pgAdmin/
│   ├── dscatalog/backend/
│   └── dscommerce-restassured/
├── java-spring-professional/
│   ├── bdsconsultas-main/ (seis desafios SQL)
│   ├── desafio-componentes-injecao-dependencia/
│   ├── desafio-consulta-vendas/
│   ├── devsuperior-ci-cd/dslist-backend/
│   ├── dscommerce/
│   ├── dscrud/
│   ├── dsevent/
│   └── spring-boot-oauth2-jwt-demo/password-grant/
└── java-spring-reference/dslearn/
```

## Validação dos documentos

- Abra os links internos para confirmar que os caminhos existem.
- Confira os comandos no diretório Maven correto (alguns projetos têm mais de um `pom.xml`).
- Confirme a versão de Java em cada `pom.xml`; os projetos da workspace não compartilham uma versão única.
- Remova contagens, datas e afirmações de conclusão quando não puderem ser confirmadas pela estrutura atual.

Os READMEs gerados pelo Spring Initializr (`HELP.md`) e arquivos auxiliares (`READ-ME.md`) podem continuar junto ao README do projeto. O README local deve ser a referência de execução e contexto específico do projeto.