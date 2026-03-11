# DSEvent - Sistema de Gerenciamento de Eventos

## 📌 Descrição

DSEvent é uma **aplicação de gerenciamento de eventos** desenvolvida em Spring Boot 3.4.2. O projeto implementa um sistema completo para criar, gerenciar e organizar eventos com suporte a participantes, categorias e outras funcionalidades.

Este projeto faz parte do curso **Java Spring Professional** do Prof. Nélio Alves.

## 🎯 Objetivos de Aprendizado

- Desenvolvimento de sistema de eventos com Spring Boot
- Relacionamentos complexos entre entidades JPA
- Gerenciamento de participantes e inscrições
- Validação avançada de dados
- Paginação e filtros
- Tratamento robusto de exceções
- Testes e documentação de APIs

## 🛠️ Tecnologias

- **Java 21**
- **Spring Boot 3.4.2**
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
cd java-spring-professional/dsevent
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

### Entidades Principais

**Evento (Event)**
```
Event
├── id: Long (PK)
├── name: String (nome do evento)
├── description: String (descrição)
├── date: LocalDateTime (data e hora do evento)
├── city: String (cidade)
├── category: Category (FK)
└── participants: List<Participant> (participantes)
```

**Participante (Participant)**
```
Participant
├── id: Long (PK)
├── name: String (nome do participante)
├── email: String (email)
├── event: Event (FK)
└── registrationDate: LocalDateTime (data de inscrição)
```

**Categoria (Category)**
```
Category
├── id: Long (PK)
├── name: String (nome da categoria)
└── events: List<Event> (eventos nesta categoria)
```

### Relacionamentos
- 1 Categoria possui N Eventos (1:N)
- 1 Evento possui N Participantes (1:N)
- Eventos são vinculados a Categorias
- Participantes são registrados em Eventos

## 🔗 Principais Endpoints

### Eventos
- `GET /events` - Listar todos os eventos com paginação
- `GET /events/{id}` - Obter detalhes de um evento
- `POST /events` - Criar novo evento
- `PUT /events/{id}` - Atualizar evento
- `DELETE /events/{id}` - Deletar evento

### Participantes
- `GET /events/{id}/participants` - Listar participantes de um evento
- `POST /events/{id}/participants` - Registrar novo participante
- `DELETE /participants/{id}` - Remover participante
- `GET /participants/{id}` - Obter detalhes do participante

### Categorias
- `GET /categories` - Listar categorias
- `GET /categories/{id}` - Obter categoria
- `POST /categories` - Criar categoria
- `PUT /categories/{id}` - Atualizar categoria
- `DELETE /categories/{id}` - Deletar categoria

## 📚 Conceitos Implementados

- **Relacionamentos JPA** - OneToMany, ManyToOne
- **Cascade Operations** - Operações em cascata entre entidades
- **Validação em Cascata** - @Valid em listas de entidades
- **Paginação** - Busca com Page<T>
- **DTOs** - Transferência segura de dados
- **Exceções Personalizadas** - Tratamento robusto de erros
- **Transações** - @Transactional para operações complexas
- **Auditoria** - Rastreamento de datas de criação/atualização

## 🧪 Testando a Aplicação

### Com cURL:

**Listar eventos:**
```bash
curl "http://localhost:8080/events?page=0&size=10"
```

**Criar novo evento:**
```bash
curl -X POST http://localhost:8080/events \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Tech Conference 2024",
    "description": "Conferência de tecnologia",
    "date": "2024-12-15T09:00:00",
    "city": "São Paulo",
    "categoryId": 1
  }'
```

**Registrar participante em evento:**
```bash
curl -X POST http://localhost:8080/events/1/participants \
  -H "Content-Type: application/json" \
  -d '{
    "name": "João Silva",
    "email": "joao@example.com"
  }'
```

**Listar participantes de um evento:**
```bash
curl "http://localhost:8080/events/1/participants"
```

## ✅ Validações

- **Nome do Evento** - Obrigatório e único
- **Data do Evento** - Deve ser no futuro
- **Email do Participante** - Deve ser válido
- **Limite de Participantes** - Pode haver limite por evento (configurável)
- **Duplicação de Participantes** - Um participante não pode se inscrever duas vezes

## 📊 Instância de Dados

O sistema carrega automaticamente dados de teste incluindo:
- 3-5 categorias de eventos
- 10-15 eventos variados
- Participantes pré-inscritos em eventos

## 🔍 Filtros e Buscas

Exemplos de filtros disponíveis:
```bash
# Eventos por categoria
GET /events?categoryId=1&page=0

# Eventos por cidade
GET /events?city=SaoPaulo&page=0

# Eventos ordenados por data
GET /events?sort=date,asc
```

## 📝 Notas Importantes

- O banco de dados H2 é reinicializado a cada execução
- Datas devem estar no formato ISO: `YYYY-MM-DDTHH:mm:ss`
- Emails são validados no formato padrão
- Limite de participantes por página: 20

## 🔧 Próximas Melhorias

- [ ] Sistema de inscrição com confirmação por email
- [ ] Geração de QR Code para entrada
- [ ] Mapa de localização dos eventos
- [ ] Sistema de avaliação de eventos
- [ ] Notificações para participantes
- [ ] Exportação de lista de presença
- [ ] Integração com calendário (Google Calendar, Outlook)
- [ ] Dashboard de estatísticas de eventos

---

**Desenvolvido durante o curso Java Spring Professional - DevSuperior** 🎓

