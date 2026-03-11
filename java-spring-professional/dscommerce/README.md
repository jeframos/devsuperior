# DSCommerce

## 📌 Descrição

DSCommerce é uma **aplicação completa de e-commerce** desenvolvida em Spring Boot 3.4.3, implementando um sistema robusto de gerenciamento de pedidos, produtos e clientes.

Este projeto faz parte do curso **Java Spring Professional** do Prof. Nélio Alves.

## 🎯 Objetivos de Aprendizado

- Desenvolvimento de um sistema e-commerce completo
- Arquitetura em camadas (Controller, Service, Repository)
- Relacionamentos complexos entre entidades JPA
- Operações CRUD avançadas
- Paginação e filtros
- Validação de dados com Bean Validation
- Tratamento robusto de exceções
- Segurança em aplicações web

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
cd java-spring-professional/dscommerce
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

O sistema DSCommerce possui as seguintes entidades principais:

- **Usuário (User)** - Usuários do sistema com roles
- **Produto (Product)** - Catálogo de produtos
- **Pedido (Order)** - Pedidos realizados pelos clientes
- **Item do Pedido (OrderItem)** - Itens que compõem cada pedido
- **Categoria (Category)** - Categorização de produtos

### Relacionamentos
- 1 Usuário possui N Pedidos
- 1 Pedido contém N Itens
- 1 Produto pode estar em N Itens de Pedido
- 1 Produto pertence a 1 Categoria

## 🔗 Principais Endpoints

### Produtos
- `GET /products` - Listar produtos com paginação
- `GET /products/{id}` - Obter detalhes do produto
- `POST /products` - Criar novo produto (admin)
- `PUT /products/{id}` - Atualizar produto (admin)
- `DELETE /products/{id}` - Deletar produto (admin)

### Pedidos
- `GET /orders` - Listar pedidos do usuário
- `GET /orders/{id}` - Obter detalhes do pedido
- `POST /orders` - Criar novo pedido
- `DELETE /orders/{id}` - Cancelar pedido

### Usuários
- `GET /users/{id}` - Obter dados do usuário
- `POST /users` - Registrar novo usuário
- `PUT /users/{id}` - Atualizar perfil do usuário

## 📚 Conceitos Implementados

- **CRUD Completo** - Create, Read, Update, Delete de todos os recursos
- **Paginação e Ordenação** - Busca eficiente com Page<T>
- **Validação em Camadas** - Validação em DTOs e entidades
- **Transações** - @Transactional para consistência dos dados
- **Repositórios** - JpaRepository com queries customizadas
- **Mapeamento Objeto-Relacional** - JPA e Hibernate
- **Segurança Básica** - Controle de acesso aos recursos

## 🔐 Funcionalidades de Segurança

- Controle de acesso por roles (USER, ADMIN)
- Verificação de propriedade de recursos
- Validação de dados de entrada
- Tratamento seguro de exceções

## 📝 Notas Importantes

- O banco de dados H2 é reinicializado a cada execução
- Scripts SQL de inicialização estão em `src/main/resources/data.sql`
- A aplicação usa um usuário padrão para testes
- Senhas são armazenadas com hash (bcrypt)

## 🧪 Testando a Aplicação

### Com Postman ou cURL:

1. **Listar produtos:**
```bash
curl http://localhost:8080/products
```

2. **Criar novo pedido:**
```bash
curl -X POST http://localhost:8080/orders \
  -H "Content-Type: application/json" \
  -d '{"productId": 1, "quantity": 2}'
```

## 🔧 Próximas Melhorias

- [ ] Implementar autenticação JWT
- [ ] Adicionar testes automatizados
- [ ] Documentação Swagger/OpenAPI
- [ ] Sistema de pagamento integrado
- [ ] Notificações por email
- [ ] Cache de produtos
- [ ] Integração com API externa de CEP

---

**Desenvolvido durante o curso Java Spring Professional - DevSuperior** 🎓

