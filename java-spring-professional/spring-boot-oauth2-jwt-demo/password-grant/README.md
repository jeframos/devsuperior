# Spring Boot OAuth2 JWT Demo

## 📌 Descrição

Coleção de **implementações de referência** para OAuth2 Authorization Server e Resource Server com Spring Boot, usando **JWT (JSON Web Tokens)** com o fluxo de concessão **password grant type**.

Este projeto faz parte do curso **Java Spring Professional** do Prof. Nélio Alves e demonstra como implementar segurança robusta em microserviços.

## 🎯 Objetivos de Aprendizado

- Entender OAuth2 e seus fluxos de autenticação
- Implementar Authorization Server com Spring Security
- Implementar Resource Server protegido
- Usar JWT como token de acesso
- Diferentes versões do Spring Boot (2.7.x, 3.0.x, 3.1.x, 3.4.x)
- Testes com Postman
- Segurança em aplicações distribuídas

## 🛠️ Tecnologias

- **Java 11/17/21** (depende da versão do Spring Boot)
- **Spring Boot** (múltiplas versões)
  - 2.7.3 (versão anterior)
  - 3.0.4 (migração para Jakarta)
  - 3.1.0 (LTS)
  - 3.4.3 (versão mais recente)
- **Spring Security**
- **OAuth2 Resource Server**
- **JWT (JSON Web Tokens)**
- **Maven**

## 📋 Requisitos

- Java 11+ instalado
- Maven 3.8+ instalado
- Postman ou cURL para testes
- Conhecimento de OAuth2 e segurança

## 🏗️ Estrutura do Projeto

```
password-grant/
├── postman-collection.json      # Coleção Postman para testes
├── postman-environment.json     # Variáveis de ambiente
├── README.md                    # Este arquivo
├── spring-boot-2-7-3/           # Versão Spring Boot 2.7.3
├── spring-boot-3-0-4/           # Versão Spring Boot 3.0.4
├── spring-boot-3-1-0/           # Versão 3.1.0 (com Auth)
├── spring-boot-3-1-0-noauth/    # Versão 3.1.0 (sem Auth)
├── spring-boot-3-4-3/           # Versão Spring Boot 3.4.3 (com Auth)
├── spring-boot-3-4-3-noauth/    # Versão 3.4.3 (sem Auth)
├── authorization-server-2-7-3/  # Auth Server 2.7.3
├── authorization-server-3-0-4/  # Auth Server 3.0.4
├── resource-server-2-7-3/       # Resource Server 2.7.3
└── resource-server-3-0-4/       # Resource Server 3.0.4
```

## 🚀 Como Executar

### Opção 1: Authorization Server + Resource Server em uma Aplicação

```bash
# Versão Spring Boot 3.1.0 (com autenticação integrada)
cd spring-boot-3-1-0
mvn spring-boot:run
```

Será executado em: `http://localhost:8080`

### Opção 2: Authorization Server Separado (Microserviços)

```bash
# Terminal 1 - Authorization Server
cd authorization-server-3-0-4
mvn spring-boot:run

# Terminal 2 - Resource Server
cd resource-server-3-0-4
mvn spring-boot:run
```

## 🔐 Fluxo OAuth2 - Password Grant Type

### Passo 1: Fazer Login (Obter Token)
```bash
POST /oauth2/token
Content-Type: application/x-www-form-urlencoded

grant_type=password
&username=alex@gmail.com
&password=123456
&client_id=myclient
&client_secret=secret
```

**Resposta:**
```json
{
  "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "token_type": "Bearer",
  "expires_in": 3600
}
```

### Passo 2: Usar Token para Acessar Recursos Protegidos
```bash
GET /products/1
Authorization: Bearer <access_token>
```

## 📊 Usuários de Teste

| Email | Senha | Role |
|---|---|---|
| alex@gmail.com | 123456 | USER |
| maria@gmail.com | 123456 | ADMIN |

## 🔗 Endpoints

### Públicos (sem autenticação)
```bash
GET /products              # Listar produtos
```

### Protegidos (requerem token)
```bash
GET /products/{id}         # Ver detalhes (USER)
POST /products             # Criar produto (ADMIN)
PUT /products/{id}         # Atualizar (ADMIN)
DELETE /products/{id}      # Deletar (ADMIN)
```

## 🧪 Testando com Postman

### Importar Collection
1. Abra Postman
2. Clique em "Import"
3. Selecione: `postman-collection.json` e `postman-environment.json`

### Executar Testes

**PARTE 1: Não autenticado**
```
GET /products              ✅ Retorna produtos
GET /products/1            ❌ Retorna 401 (não autorizado)
POST /products             ❌ Retorna 401
```

**PARTE 2: Login como Alex (USER)**
```
POST /oauth2/token         ✅ Retorna token JWT
GET /products              ✅ Acesso permitido
GET /products/1            ✅ Acesso permitido
POST /products             ❌ Retorna 403 (não é admin)
```

**PARTE 3: Login como Maria (ADMIN)**
```
POST /oauth2/token         ✅ Retorna token JWT
GET /products              ✅ Acesso permitido
POST /products             ✅ Pode criar produtos
DELETE /products/{id}      ✅ Pode deletar produtos
```

## 🧪 Testando com cURL

### Obter Token
```bash
curl -X POST http://localhost:8080/oauth2/token \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "grant_type=password&username=alex@gmail.com&password=123456&client_id=myclient&client_secret=secret"
```

### Usar Token
```bash
TOKEN="seu_token_aqui"

curl -X GET http://localhost:8080/products/1 \
  -H "Authorization: Bearer $TOKEN"
```

## 📚 Conceitos-Chave

### OAuth2 Password Grant Type
```
Usuário fornece username + password
↓
Authorization Server valida credenciais
↓
Emite JWT token
↓
Cliente usa token para acessar Resource Server
↓
Resource Server valida token e retorna recurso
```

### JWT - JSON Web Token
Composto de 3 partes separadas por pontos:
```
Header.Payload.Signature

Header:    {"type": "JWT", "alg": "HS256"}
Payload:   {"sub": "usuario", "iat": 1516239022}
Signature: HMACSHA256(base64(header) + "." + base64(payload))
```

### Validar Token em jwt.io
1. Acesse https://jwt.io
2. Cole seu token no campo "Encoded"
3. Verifique payload e signature

## ⚠️ Notas Importantes

### 1. Configurar Variáveis Postman
No `postman-environment.json`:
```json
{
  "ashost": "http://localhost:8080",
  "token": "{{ Será preenchido automaticamente }}",
  "username": "alex@gmail.com"
}
```

### 2. Endpoints Podem Variar por Versão
- Spring Boot 2.7.x: `/oauth/token`
- Spring Boot 3.0.x+: `/oauth2/token`

Ajuste nos seus requests conforme necessário!

### 3. Secret JWT em Produção
Mude a chave secreta! Configurada em `application.properties`:
```properties
security.jwt.secret=sua-chave-super-secreta-com-mais-de-32-caracteres
```

## 🔄 Migrações entre Versões

### Spring Boot 2.7.x → 3.0.x
- **Mudança**: `javax.*` → `jakarta.*`
- **Exemplo**: `javax.servlet` → `jakarta.servlet`
- **Impacto**: Reimplementar imports

### Spring Boot 3.0.x → 3.1.x+
- Melhor suporte a OAuth2/OIDC
- Novas anotações de segurança
- Performance aprimorada

## 🔧 Próximas Melhorias

- [ ] Implementar Refresh Token
- [ ] Integração com OAuth2 de provedores (Google, GitHub)
- [ ] Rate limiting e throttling
- [ ] Audit logs
- [ ] MFA (Multi-Factor Authentication)

## 📚 Recursos Úteis

- [OAuth 2.0 RFC 6749](https://tools.ietf.org/html/rfc6749)
- [JWT.io - Debugger](https://jwt.io)
- [Spring Security Docs](https://docs.spring.io/spring-security/reference/)

---

**Desenvolvido durante o curso Java Spring Professional - DevSuperior** 🎓
 
