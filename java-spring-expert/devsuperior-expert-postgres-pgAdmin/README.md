# PostgreSQL e pgAdmin com Docker Compose

Infraestrutura local de apoio às aulas de Java Spring Expert sobre PostgreSQL e pgAdmin. Não é uma aplicação Spring; os serviços e portas são definidos em `docker-compose.yml`.

## Subir os serviços

Na pasta deste arquivo:

```powershell
docker compose up -d
```

- PostgreSQL: porta `5433` no host, banco `mydatabase`.
- pgAdmin: interface em `http://localhost:5050`.

O Compose define também os volumes de dados em `.data/`. As credenciais de desenvolvimento estão definidas no próprio arquivo Compose. Altere-as antes de expor os serviços fora do ambiente local e não use esses valores em produção.

Para encerrar os serviços mantendo os dados:

```powershell
docker compose down
```

O antigo `read-me.txt` é mantido como anotação original do módulo; este README documenta o Compose atualmente presente.