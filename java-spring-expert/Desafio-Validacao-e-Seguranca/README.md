# 🎫 Desafio Validação e Segurança - BDS04

## 📋 Descrição

Sistema de gerenciamento de **Cidades e Eventos** desenvolvido como um desafio do Bootcamp Spring React pela DevSuperior. O projeto implementa validação robusta de dados, autenticação OAuth2 e testes de integração com MockMvc.

**Funcionalidades:**
- ✅ CRUD completo de Cidades e Eventos
- ✅ Autenticação OAuth2 com JWT
- ✅ Controle de acesso baseado em roles (ROLE_OPERATOR, ROLE_ADMIN)
- ✅ Validação customizada de datas
- ✅ Testes de integração com cobertura de cenários
- ✅ API RESTful com tratamento de erros

---

## 🛠️ Tecnologias

| Tecnologia | Versão | Descrição |
|-----------|--------|-----------|
| **Java** | 21 | Linguagem de programação |
| **Spring Boot** | 3.4.3 | Framework web |
| **Spring Data JPA** | 3.4.3 | ORM e persistência |
| **Spring Security** | 3.4.3 | Autenticação e autorização |
| **OAuth2** | 3.4.3 | Gerenciamento de tokens JWT |
| **Validation** | 3.4.3 | Validação de dados |
| **H2 Database** | - | Banco de dados em memória (testes) |
| **JUnit 5** | - | Framework de testes |
| **Mockito** | - | Mocks para testes |
| **Maven** | 3.8+ | Build e gerenciamento de dependências |

---

## 📦 Pré-requisitos

- **Java 21** instalado
- **Maven 3.8+** instalado
- **Git** para clonar o repositório

### Verificar instalação:
```bash
java -version
mvn -version
```

---

## 🚀 Como Executar

### 1️⃣ Clonar o repositório
```bash
git clone https://github.com/seu-usuario/bds04.git
cd "Desafio Validação e Segurança"
```

### 2️⃣ Compilar o projeto
```bash
mvn clean compile
```

### 3️⃣ Executar a aplicação
```bash
mvn spring-boot:run
```

A aplicação estará disponível em: **http://localhost:8080**

### 4️⃣ Executar testes
```bash
# Todos os testes
mvn test

# Apenas testes de Controller
mvn test -Dtest=EventControllerIT

# Teste específico
mvn test -Dtest=EventControllerIT#insertShouldReturn422WhenAdminLoggedAndPastDate
```

---

## 🔐 Autenticação OAuth2

### Obter Token de Acesso

**Endpoint:** `POST /oauth2/token`

**Credenciais disponíveis:**

1. **Operador** (ROLE_OPERATOR)
   - Email: `ana@gmail.com`
   - Senha: `123456`

2. **Admin** (ROLE_ADMIN)
   - Email: `bob@gmail.com`
   - Senha: `123456`

### Exemplo de Requisição com cURL:

```bash
curl -X POST http://localhost:8080/oauth2/token \
  -H "Authorization: Basic bXljbGllbnRpZDpteWNsaWVudHNlY3JldA==" \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "grant_type=password&username=ana@gmail.com&password=123456"
```

**Resposta:**
```json
{
  "access_token": "eyJraWQiOiI...",
  "token_type": "Bearer",
  "expires_in": 86400
}
```

### Usar Token nas Requisições
```bash
Authorization: Bearer {token_aqui}
```

---

## 📡 Endpoints da API

### 🏙️ Cidades

| Método | Endpoint | Descrição | Autenticação |
|--------|----------|-----------|--------------|
| GET | `/cities` | Listar todas as cidades | Pública |
| POST | `/cities` | Criar nova cidade | ROLE_ADMIN |
| PUT | `/cities/{id}` | Atualizar cidade | ROLE_ADMIN |
| DELETE | `/cities/{id}` | Deletar cidade | ROLE_ADMIN |

### 🎫 Eventos

| Método | Endpoint | Descrição | Autenticação |
|--------|----------|-----------|--------------|
| GET | `/events` | Listar eventos (paginado) | Pública |
| POST | `/events` | Criar novo evento | ROLE_OPERATOR, ROLE_ADMIN |
| PUT | `/events/{id}` | Atualizar evento | ROLE_ADMIN |
| DELETE | `/events/{id}` | Deletar evento | ROLE_ADMIN |

---

## 📝 Exemplos de Uso

### Criar um Evento (POST /events)

**Requisição:**
```bash
curl -X POST http://localhost:8080/events \
  -H "Authorization: Bearer {token}" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Conferência Tech 2026",
    "date": "2026-06-15",
    "url": "https://conferencia.tech",
    "cityId": 1
  }'
```

**Resposta (201 Created):**
```json
{
  "id": 5,
  "name": "Conferência Tech 2026",
  "date": "2026-06-15",
  "url": "https://conferencia.tech",
  "cityId": 1
}
```

### Listar Eventos (GET /events)

**Requisição:**
```bash
curl http://localhost:8080/events?page=0&size=10&sort=date,asc
```

**Resposta (200 OK):**
```json
{
  "content": [
    {
      "id": 1,
      "name": "Feira do Software",
      "date": "2021-05-16",
      "url": "https://feiradosoftware.com",
      "cityId": 1
    }
  ],
  "pageable": {...},
  "totalPages": 1,
  "totalElements": 4
}
```

---

## ✅ Validações

### EventDTO - Validação de Campos

| Campo | Validação | Mensagem |
|-------|-----------|----------|
| `name` | @NotBlank | "Campo requerido" |
| `date` | @FutureOrPresent | "A data do evento não pode ser passada" |
| `cityId` | @NotNull | "Campo requerido" |

### CityDTO - Validação de Campos

| Campo | Validação | Mensagem |
|-------|-----------|----------|
| `name` | @NotBlank | "Campo requerido" |

### Erros de Validação

**Status:** 422 (Unprocessable Entity)

**Exemplo de Resposta:**
```json
{
  "timestamp": "2026-05-14T14:05:21.713016500Z",
  "status": 422,
  "error": "Validation exception!",
  "message": "Validation failed for argument [0]...",
  "path": "/events",
  "errors": [
    {
      "fieldName": "date",
      "message": "A data do evento não pode ser passada"
    }
  ]
}
```

---

## 🏗️ Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/devsuperior/bds04/
│   │   ├── entities/           # Entidades JPA
│   │   │   ├── City.java
│   │   │   └── Event.java
│   │   ├── dto/                # Data Transfer Objects
│   │   │   ├── CityDTO.java
│   │   │   ├── EventDTO.java
│   │   │   ├── FutureOrPresent.java (anotação customizada)
│   │   │   └── FutureOrPresentValidator.java
│   │   ├── repositories/       # Interfaces de acesso a dados
│   │   │   ├── CityRepository.java
│   │   │   └── EventRepository.java
│   │   ├── services/           # Lógica de negócio
│   │   │   ├── CityService.java
│   │   │   └── EventService.java
│   │   ├── resources/          # Controllers REST
│   │   │   ├── CityResource.java
│   │   │   ├── EventResource.java
│   │   │   └── exceptions/
│   │   │       └── ResourceExceptionHandler.java
│   │   └── Bds04Application.java
│   └── resources/
│       ├── application.properties
│       ├── application-test.properties
│       └── import.sql
└── test/
    └── java/com/devsuperior/bds04/
        ├── controllers/
        │   ├── CityControllerIT.java
        │   └── EventControllerIT.java
        └── tests/
            └── TokenUtil.java
```

---

## 🧪 Testes de Integração

Os testes cobrem:
- ✅ Autenticação com token inválido
- ✅ Criação de eventos com dados corretos
- ✅ Validação de campos vazios
- ✅ Validação de datas (passada/futura)
- ✅ Validação de cidade nula
- ✅ Paginação de resultados

---

## 🔍 Dados de Teste

### Usuários Pré-carregados
```sql
ana@gmail.com (ROLE_OPERATOR)
bob@gmail.com (ROLE_ADMIN)
```

### Cidades Pré-carregadas
- São Paulo
- Brasília
- Fortaleza
- Salvador
- Manaus
- Curitiba
- Goiânia
- Belém
- Porto Alegre
- Rio de Janeiro
- Belo Horizonte

### Eventos Pré-carregados
- Feira do Software (São Paulo)
- CCXP (São Paulo)
- Congresso Linux (Brasília)
- Semana Spring React (Fortaleza)

---

## 🐛 Tratamento de Erros

### Status HTTP Utilizados

| Status | Significado |
|--------|------------|
| 200 | OK - Requisição bem-sucedida |
| 201 | Created - Recurso criado com sucesso |
| 400 | Bad Request - Dados inválidos |
| 401 | Unauthorized - Token inválido ou ausente |
| 403 | Forbidden - Sem permissão |
| 404 | Not Found - Recurso não encontrado |
| 422 | Unprocessable Entity - Erro de validação |
| 500 | Internal Server Error - Erro do servidor |

---

## 📚 Recursos Úteis

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Security OAuth2](https://spring.io/projects/spring-security)
- [Jakarta Validation](https://jakarta.ee/specifications/validation/)
- [DevSuperior](https://devsuperior.com.br)

---

## 👨‍💻 Autor

Desenvolvido como parte do Bootcamp Spring React - DevSuperior

---

## 📄 Licença

Este projeto está disponível para fins educacionais.

---

## 💡 Dicas de Desenvolvimento

1. **Use Postman ou Insomnia** para testar os endpoints
2. **Configure CORS** no `application.properties` conforme necessário
3. **Monitore logs** com `mvn spring-boot:run`
4. **Teste endpoints públicos primeiro** antes de testar com autenticação
5. **Use `LocalDate.now()`** para datas dinâmicas nos testes

---

**Última atualização:** 2026-05-14

