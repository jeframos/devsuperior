# DSCrud - Sistema de Gerenciamento de Clientes

## 📌 Descrição

DSCrud é uma **aplicação CRUD completa** para gerenciamento de clientes desenvolvida em Spring Boot 3.4.3. O projeto implementa todas as operações básicas (Create, Read, Update, Delete) com paginação, ordenação e validação robusta.

Este projeto faz parte do curso **Java Spring Professional** do Prof. Nélio Alves e é ideal para aprender os fundamentos de uma API REST.

## 🎯 Objetivos de Aprendizado

- Implementação de API REST com Spring Boot
- Operações CRUD completas
- Paginação e ordenação de dados
- Validação de entrada com Bean Validation
- Tratamento de exceções personalizado
- Testes de endpoints com Postman
- Boas práticas em desenvolvimento de APIs

## 🛠️ Tecnologias

- **Java 21**
- **Spring Boot 3.4.3**
- **Spring Data JPA**
- **H2 Database** (desenvolvimento)
- **Validação Bean** (javax.validation)
- **Maven**

## 📋 Requisitos

- Java 21 instalado
- Maven 3.8+ instalado

## 🚀 Como Executar

### 1. Navegar para o projeto
```bash
cd java-spring-professional/dscrud
```

### 2. Compilar o projeto
```bash
mvn clean install
```

### 3. Executar a aplicação
```bash
mvn spring-boot:run
```

A aplicação estará disponível em: `http://localhost:8080`

## 📊 Modelo de Dados

### Entidade: Cliente (Client)

```
Cliente
├── id: Long (PK)
├── name: String (obrigatório)
├── cpf: String (obrigatório, único)
├── income: BigDecimal (renda)
├── birthDate: LocalDate (data de nascimento)
├── children: Integer (número de filhos)
└── createdAt: LocalDateTime (auditoria)
```

## 🔗 Endpoints Implementados

### Buscar Cliente por ID
```bash
GET /clients/{id}
Resposta: 200 OK com dados do cliente
```

### Listar Clientes com Paginação
```bash
GET /clients?page=0&size=6&sort=name,asc
Parâmetros:
  - page: número da página (padrão: 0)
  - size: quantidade de registros por página (padrão: 12)
  - sort: campo e direção de ordenação (padrão: id,asc)

Resposta: 200 OK com lista paginada de clientes
```

### Criar Novo Cliente
```bash
POST /clients
Content-Type: application/json

Body:
{
  "name": "Maria Silva",
  "cpf": "12345678901",
  "income": 6500.0,
  "birthDate": "1994-07-20",
  "children": 2
}

Resposta: 201 CREATED com dados do cliente criado
```

### Atualizar Cliente
```bash
PUT /clients/{id}
Content-Type: application/json

Body:
{
  "name": "Maria Silva Atualizado",
  "cpf": "12345678901",
  "income": 7000.0,
  "birthDate": "1994-07-20",
  "children": 2
}

Resposta: 200 OK com dados do cliente atualizado
```

### Deletar Cliente
```bash
DELETE /clients/{id}

Resposta: 204 NO CONTENT
```

## 📚 Conceitos Implementados

- **Paginação** - Usando Spring Data Page<T>
- **Ordenação** - Suporte a múltiplos campos de ordenação
- **Validação** - Bean Validation nos DTOs
- **DTOs** - Separação entre modelo e transferência de dados
- **Tratamento de Erros** - ControllerAdvice personalizado
- **Auditoria** - Registro automático de criação e atualização
- **Transações** - @Transactional para garantir integridade

## ✅ Validações Implementadas

- **CPF** - Não pode ser duplicado
- **Nome** - Campo obrigatório
- **Data de Nascimento** - Deve ser válida e no passado
- **Renda** - Deve ser maior que zero (se informada)
- **Filhos** - Não pode ser negativo

## 🧪 Testando a Aplicação

### Com cURL:

**Listar clientes:**
```bash
curl "http://localhost:8080/clients?page=0&size=6&sort=name"
```

**Buscar cliente por ID:**
```bash
curl http://localhost:8080/clients/1
```

**Criar novo cliente:**
```bash
curl -X POST http://localhost:8080/clients \
  -H "Content-Type: application/json" \
  -d '{
    "name": "João Santos",
    "cpf": "98765432100",
    "income": 5000.0,
    "birthDate": "1990-03-15",
    "children": 1
  }'
```

**Atualizar cliente:**
```bash
curl -X PUT http://localhost:8080/clients/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "João Santos Updated",
    "cpf": "98765432100",
    "income": 5500.0,
    "birthDate": "1990-03-15",
    "children": 1
  }'
```

**Deletar cliente:**
```bash
curl -X DELETE http://localhost:8080/clients/1
```

## 🔍 Filtros e Buscas

Exemplo de busca com filtros:
```bash
GET /clients?page=0&size=10&sort=name,asc
```

Resultado:
```json
{
  "content": [
    {
      "id": 1,
      "name": "Maria Silva",
      "cpf": "12345678901",
      "income": 6500.0,
      "birthDate": "1994-07-20",
      "children": 2
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 10,
    "sort": "name: ASC"
  },
  "totalElements": 1,
  "totalPages": 1
}
```

## 📝 Notas Importantes

- O banco de dados H2 é reinicializado a cada execução
- Dados de teste são carregados automaticamente
- CPF deve ter 11 dígitos
- Datas devem estar no formato: `YYYY-MM-DD`

## 🔧 Próximas Melhorias

- [ ] Testes automatizados com JUnit e Mockito
- [ ] Documentação Swagger/OpenAPI
- [ ] Autenticação JWT
- [ ] Busca por nome ou CPF
- [ ] Exportação de dados (CSV, PDF)
- [ ] Cache com Redis
- [ ] Validação de CPF usando algoritmo real

---

**Desenvolvido durante o curso Java Spring Professional - DevSuperior** 🎓

