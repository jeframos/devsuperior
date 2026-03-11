# BDS Consultas - Desafios SQL no URI Online Judge

## 📌 Descrição

BDS Consultas é uma **coleção de desafios SQL** resolvidos utilizando Spring Boot e SQL puro. O projeto contém múltiplas soluções para problemas do **URI Online Judge**, focando em consultas complexas, otimização e boas práticas SQL.

Faz parte do curso **Java Spring Professional** do Prof. Nélio Alves como exercício de práticaSQL avançado.

## 🎯 Objetivos de Aprendizado

- Consultas SQL complexas e otimizadas
- Joins, Subqueries e Window Functions
- GROUP BY, ORDER BY e Aggregations
- Performance de consultas
- Integração SQL com Spring Boot
- Trabalhar com diferentes tipos de dados
- Solucionar problemas reais com SQL

## 🛠️ Tecnologias

- **Java 11+**
- **Spring Boot 2.4.4**
- **Maven**
- **SQL**

## 📋 Requisitos

- Java 11 instalado
- Maven 3.8+ instalado
- Conhecimento de SQL

## 🚀 Como Executar

Cada desafio é um projeto independente. Para executar um deles:

```bash
cd java-spring-professional/bdsconsultas-main/uri{NUMERO}
mvn clean install
mvn spring-boot:run
```

## 📚 Desafios Inclusos

### [URI 2602 - Clientes com Produtos Adquiridos](./uri2602)

**Descrição**: Encontrar clientes que fizeram compras e listar os produtos que compraram.

**Conceitos**:
- INNER JOIN
- GROUP BY
- ORDER BY

**Problema SQL**:
Selecionar clientes que realizaram compras e contar quantos produtos diferentes compraram.

---

### [URI 2609 - Cidades com Clientes](./uri2609)

**Descrição**: Listar cidades que possuem clientes cadastrados.

**Conceitos**:
- SELECT DISTINCT
- ORDER BY
- WHERE com JOINs

**Problema SQL**:
Encontrar todas as cidades que têm pelo menos um cliente registrado.

---

### [URI 2611 - Venda com Produto](./uri2611)

**Descrição**: Relacionar vendas com produtos e calcular valores totais.

**Conceitos**:
- JOIN de múltiplas tabelas
- SUM() e agregações
- GROUP BY com múltiplas colunas

**Problema SQL**:
Calcular o valor total vendido por produto.

---

### [URI 2621 - Produtos Mais Vendidos](./uri2621)

**Descrição**: Identificar os produtos mais vendidos por quantidade.

**Conceitos**:
- Window Functions (ROW_NUMBER, RANK)
- ORDER BY com múltiplos critérios
- LIMIT/TOP

**Problema SQL**:
Listar produtos ordenados pela quantidade vendida em ordem decrescente.

---

### [URI 2737 - Pedidos com Dados do Cliente](./uri2737)

**Descrição**: Combinar dados de pedidos com informações dos clientes.

**Conceitos**:
- LEFT/INNER JOIN
- Subconsultas
- Agregações com CASE

**Problema SQL**:
Trazer informações combinadas de pedidos e clientes com filtros específicos.

---

### [URI 2990 - Análise Complexa de Vendas](./uri2990)

**Descrição**: Desafio avançado com múltiplas condições e JOINs complexos.

**Conceitos**:
- CTEs (Common Table Expressions) ou subconsultas
- Window Functions avançadas
- HAVING com GROUP BY

**Problema SQL**:
Análise multidimensional de dados de vendas com múltiplos filtros.

---

## 🎯 Estrutura de Cada Projeto

Cada desafio (uri####) possui:

```
uri{numero}/
├── {numero}.sql          # Arquivo SQL com a solução
├── pom.xml              # Configuração Maven
├── src/main/
│   ├── java/
│   │   └── com/devsuperior/
│   │       ├── entity/  # Entidades JPA
│   │       ├── dto/     # DTOs para transporte
│   │       └── controller/ # Endpoints REST
│   └── resources/
│       ├── application.properties
│       └── import.sql   # Scripts de inserção
└── target/             # Compilados
```

## 🔗 Endpoints Típicos

Cada projeto expõe endpoints que demonstram a execução das queries:

```bash
GET /result                    # Resultado da query principal
GET /result?param1=valor       # Com parâmetros
GET /result/count              # Contagem de registros
GET /result/export             # Exportar resultados
```

## 📊 Exemplo: URI 2602

### Arquivo SQL (2602.sql)
```sql
SELECT 
    c.name,
    COUNT(DISTINCT p.id) as products_count
FROM customers c
INNER JOIN orders o ON c.id = o.customer_id
INNER JOIN order_items oi ON o.id = oi.order_id
INNER JOIN products p ON oi.product_id = p.id
GROUP BY c.id, c.name
ORDER BY products_count DESC, c.name
```

### Endpoint
```bash
curl http://localhost:8080/customers/with-products
```

### Resposta
```json
[
  {
    "name": "João Silva",
    "productsCount": 5
  },
  {
    "name": "Maria Santos",
    "productsCount": 3
  }
]
```

## 💡 Dicas Importantes

### 1. **Otimização de Queries**
- Use indexes nas colunas frequentemente consultadas
- Prefira JOINs a subconsultas quando possível
- Use EXPLAIN para analisar planos de execução

### 2. **Testes SQL**
- Comece testando a query isoladamente no database
- Depois integre com Spring Boot
- Use H2 ou PostgreSQL para testes locais

### 3. **Boas Práticas**
```sql
-- ✅ BOM: Claro e bem estruturado
SELECT c.id, c.name, COUNT(o.id) as order_count
FROM customers c
LEFT JOIN orders o ON c.id = o.customer_id
WHERE c.active = true
GROUP BY c.id, c.name
HAVING COUNT(o.id) > 0
ORDER BY order_count DESC;

-- ❌ EVITAR: Difícil de ler e entender
SELECT c.id,c.name,COUNT(o.id)FROM customers c LEFT JOIN orders o ON 
c.id=o.customer_id WHERE c.active=1 GROUP BY c.id,c.name HAVING 
COUNT(o.id)>0 ORDER BY 3 DESC;
```

### 4. **Funções Úteis**
- `COUNT()` - Conta registros
- `SUM()` - Soma valores
- `AVG()` - Média
- `MIN()` / `MAX()` - Mínimo/Máximo
- `COALESCE()` - Valor padrão para NULL
- `CASE` - Lógica condicional

## 🧪 Testando Localmente

### Com PostgreSQL
```bash
# 1. Criar banco de dados
createdb uri_challenges

# 2. Rodar script SQL
psql uri_challenges < uri2602/2602.sql

# 3. Executar aplicação
cd uri2602
mvn spring-boot:run
```

### Com H2
```bash
# H2 é configurado automaticamente
mvn spring-boot:run
# Acesse: http://localhost:8080/h2-console
```

## 📝 Conceitos SQL Avançados

### JOINs
```sql
INNER JOIN    - Apenas registros coincidentes
LEFT JOIN     - Todos da esquerda + correspondentes da direita
RIGHT JOIN    - Todos da direita + correspondentes da esquerda
FULL OUTER    - Todos de ambas as tabelas
CROSS JOIN    - Produto cartesiano
```

### Agregações
```sql
GROUP BY      - Agrupar resultados
HAVING        - Filtrar grupos (como WHERE para GROUP BY)
ORDER BY      - Ordenar resultados
LIMIT/TOP     - Limitar quantidade de linhas
```

### Funções Analíticas
```sql
ROW_NUMBER()  - Número sequencial da linha
RANK()        - Rank com empates
DENSE_RANK()  - Rank sem gaps
LAG/LEAD      - Acessar linhas anteriores/posteriores
```

## 🔧 Próximas Melhorias

- [ ] Adicionar mais desafios URI
- [ ] Testes de performance
- [ ] Visualização de planos de execução
- [ ] Comparação de diferentes abordagens
- [ ] Documentação Swagger para APIs
- [ ] Dashboard com resultados

## 📚 Recursos Úteis

- [URI Online Judge](https://www.urionlinejudge.com.br/) - Plataforma original
- [SQL Documentation](https://www.postgresql.org/docs/) - PostgreSQL Docs
- [W3Schools SQL](https://www.w3schools.com/sql/) - Tutorial SQL

## 🎓 Estrutura de Aprendizado

1. **Iniciante**: URI 2602, 2609, 2611
2. **Intermediário**: URI 2621, 2737
3. **Avançado**: URI 2990

---

**Desenvolvido durante o curso Java Spring Professional - DevSuperior** 🎓

