# DSCatalog Backend

## 📌 Descrição

DSCatalog é uma API REST desenvolvida em Spring Boot 3.5.7 que implementa um **catálogo de produtos** com funcionalidades avançadas de gerenciamento.

Este projeto faz parte do curso **Java Spring Expert** do Prof. Nélio Alves.

## 🎯 Objetivos de Aprendizado

- Arquitetura de APIs REST com Spring Boot
- Padrão DTO e mapeamento com ModelMapper
- JPA/Hibernate e relacionamentos de banco de dados
- Paginação e filtros avançados
- Validação de dados
- Tratamento de exceções personalizado
- Segurança com Spring Security
- Testes automatizados

## 🛠️ Tecnologias

- **Java 21**
- **Spring Boot 3.5.7**
- **Spring Data JPA**
- **PostgreSQL** (produção)
- **H2 Database** (desenvolvimento/testes)
- **Maven**

## 📋 Requisitos

- Java 21 instalado
- Maven 3.8+ instalado
- PostgreSQL (opcional - para desenvolvimento)

## 🚀 Como Executar

### 1. Clonar/Navegar para o projeto
```bash
cd java-spring-expert/dscatalog/backend
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

### 4. Usar o Banco de Dados H2 (padrão)
Acesse o console H2: `http://localhost:8080/h2-console`

## 📊 Estrutura do Banco de Dados

O projeto utiliza JPA com migrações automáticas. As principais entidades são:

- **Produto** - Informações do produto
- **Categoria** - Categorias de produtos
- **Usuário** - Usuários do sistema
- **Papel (Role)** - Papéis/permissões

## 🔗 Principais Endpoints

### Produtos
- `GET /products` - Listar todos os produtos
- `GET /products/{id}` - Obter produto por ID
- `POST /products` - Criar novo produto
- `PUT /products/{id}` - Atualizar produto
- `DELETE /products/{id}` - Deletar produto

### Categorias
- `GET /categories` - Listar categorias
- `GET /categories/{id}` - Obter categoria por ID
- `POST /categories` - Criar categoria
- `PUT /categories/{id}` - Atualizar categoria
- `DELETE /categories/{id}` - Deletar categoria

## 📚 Conceitos Principais Implementados

- **DTOs (Data Transfer Objects)** - Transferência segura de dados
- **Paginação** - Suporte a queries paginadas
- **Validação** - Bean Validation com customização
- **Exceções Personalizadas** - Tratamento robusto de erros
- **Auditoria** - Rastreamento de criação/atualização
- **Segurança** - Controle de acesso baseado em papéis (RBAC)

## 📝 Notas Importantes

- O projeto usa H2 por padrão para facilitar execução
- Para usar PostgreSQL, configure as propriedades em `application-prod.properties`
- Dados de teste são carregados automaticamente através de scripts SQL

## 🔧 Próximas Melhorias

- [ ] Implementar autenticação JWT
- [ ] Adicionar cobertura de testes
- [ ] Documentação com Swagger
- [ ] Paginação mais avançada
- [ ] Cache com Redis

---

**Desenvolvido durante o curso Java Spring Expert - DevSuperior** 🎓

