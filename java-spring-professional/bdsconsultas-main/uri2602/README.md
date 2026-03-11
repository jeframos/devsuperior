# URI 2602 - Clientes com Produtos Adquiridos

## 📌 Descrição

Desafio que envolve encontrar clientes que realizaram compras e contar quantos produtos diferentes cada um comprou.

**Dificuldade**: ⭐ Básico/Intermediário

## 🎯 O Desafio

Escrever uma query SQL que:
1. Seleciona clientes que fizeram pelo menos uma compra
2. Conta a quantidade de produtos diferentes adquiridos
3. Ordena os resultados por quantidade (decrescente) e depois por nome

## 🛠️ Tecnologias

- **Java 11**
- **Spring Boot 2.4.4**
- **H2 Database / PostgreSQL**

## 🚀 Como Executar

```bash
cd uri2602
mvn clean install
mvn spring-boot:run
```

Acesse: `http://localhost:8080`

## 📊 Estrutura de Dados

### Tabelas Utilizadas

**customers**
- id (PK)
- name
- email

**orders**
- id (PK)
- customer_id (FK)
- order_date

**order_items**
- id (PK)
- order_id (FK)
- product_id (FK)
- quantity
- price

**products**
- id (PK)
- name
- price

## 🔍 Solução SQL

```sql
SELECT 
    c.name,
    COUNT(DISTINCT oi.product_id) as product_count
FROM customers c
INNER JOIN orders o ON c.id = o.customer_id
INNER JOIN order_items oi ON o.id = oi.order_id
GROUP BY c.id, c.name
ORDER BY product_count DESC, c.name ASC;
```

## 📝 Explicação da Query

```sql
-- Selecionamos o nome do cliente e contamos produtos distintos
SELECT c.name, COUNT(DISTINCT oi.product_id) as product_count

-- Começamos com a tabela de clientes
FROM customers c

-- Relacionamos com pedidos (apenas clientes que compraram)
INNER JOIN orders o ON c.id = o.customer_id

-- Relacionamos com itens do pedido
INNER JOIN order_items oi ON o.id = oi.order_id

-- Agrupamos por cliente
GROUP BY c.id, c.name

-- Ordenamos por quantidade de produtos (decrescente) e nome
ORDER BY product_count DESC, c.name ASC;
```

## 💡 Conceitos-Chave

### INNER JOIN
```sql
INNER JOIN orders o ON c.id = o.customer_id
```
- Retorna apenas registros onde há correspondência
- Exclui clientes sem pedidos

### COUNT(DISTINCT ...)
```sql
COUNT(DISTINCT oi.product_id)
```
- Conta produtos **únicos**
- Ignore duplicatas se um produto foi comprado múltiplas vezes

### GROUP BY
```sql
GROUP BY c.id, c.name
```
- Agrupa resultados por cliente
- Necessário quando usamos funções de agregação

## 🧪 Dados de Teste

O projeto inclui dados de teste que são carregados automaticamente.

### Exemplo de Resultado Esperado

| name | product_count |
|------|---|
| João Silva | 5 |
| Maria Santos | 3 |
| Pedro Oliveira | 2 |

## 🔗 Endpoints REST

```bash
# Listar todos os clientes com contagem de produtos
GET /customers/with-products

# Obter cliente específico com seus produtos
GET /customers/{id}/products

# Contar total de clientes que fizeram compras
GET /customers/count/with-orders

# Exportar resultado em CSV
GET /customers/export/with-products
```

## 💻 Exemplo de Resposta JSON

```json
[
  {
    "id": 1,
    "name": "João Silva",
    "productCount": 5,
    "totalSpent": 1500.50
  },
  {
    "id": 2,
    "name": "Maria Santos",
    "productCount": 3,
    "totalSpent": 899.99
  }
]
```

## 📈 Variações do Desafio

### Variação 1: Incluir valor total gasto
```sql
SELECT 
    c.name,
    COUNT(DISTINCT oi.product_id) as product_count,
    SUM(oi.quantity * oi.price) as total_spent
FROM customers c
INNER JOIN orders o ON c.id = o.customer_id
INNER JOIN order_items oi ON o.id = oi.order_id
GROUP BY c.id, c.name
ORDER BY total_spent DESC;
```

### Variação 2: Apenas clientes que compraram mais de 3 produtos
```sql
SELECT 
    c.name,
    COUNT(DISTINCT oi.product_id) as product_count
FROM customers c
INNER JOIN orders o ON c.id = o.customer_id
INNER JOIN order_items oi ON o.id = oi.order_id
GROUP BY c.id, c.name
HAVING COUNT(DISTINCT oi.product_id) > 3
ORDER BY product_count DESC;
```

### Variação 3: Com data do pedido
```sql
SELECT 
    c.name,
    COUNT(DISTINCT oi.product_id) as product_count,
    MAX(o.order_date) as last_purchase_date
FROM customers c
INNER JOIN orders o ON c.id = o.customer_id
INNER JOIN order_items oi ON o.id = oi.order_id
GROUP BY c.id, c.name
ORDER BY product_count DESC;
```

## ⚠️ Armadilhas Comuns

### ❌ ERRADO: Usar LEFT JOIN quando deveria usar INNER JOIN
```sql
-- Isso incluiria clientes sem compras!
SELECT c.name, COUNT(DISTINCT oi.product_id)
FROM customers c
LEFT JOIN orders o ON c.id = o.customer_id  -- ❌
LEFT JOIN order_items oi ON o.id = oi.order_id
GROUP BY c.id, c.name;
```

### ❌ ERRADO: Esquecer DISTINCT na contagem
```sql
-- Isso contaria o mesmo produto múltiplas vezes
SELECT c.name, COUNT(oi.product_id)  -- ❌
FROM customers c
INNER JOIN orders o ON c.id = o.customer_id
INNER JOIN order_items oi ON o.id = oi.order_id
GROUP BY c.id, c.name;
```

### ✅ CORRETO: Sempre usar DISTINCT em COUNT
```sql
SELECT c.name, COUNT(DISTINCT oi.product_id)
FROM customers c
INNER JOIN orders o ON c.id = o.customer_id
INNER JOIN order_items oi ON o.id = oi.order_id
GROUP BY c.id, c.name;
```

## 🔧 Otimizações

### 1. Adicionar Índices
```sql
CREATE INDEX idx_orders_customer_id ON orders(customer_id);
CREATE INDEX idx_order_items_order_id ON order_items(order_id);
CREATE INDEX idx_order_items_product_id ON order_items(product_id);
```

### 2. Usar Explain para Analisar
```sql
EXPLAIN ANALYZE
SELECT c.name, COUNT(DISTINCT oi.product_id)
FROM customers c
INNER JOIN orders o ON c.id = o.customer_id
INNER JOIN order_items oi ON o.id = oi.order_id
GROUP BY c.id, c.name;
```

## 📚 Recursos para Aprender Mais

- [JOIN Visual Explanation](https://www.w3schools.com/sql/sql_join.asp)
- [GROUP BY Explained](https://www.postgresql.org/docs/current/sql-select.html#SQL-GROUPBY)
- [SQL COUNT Function](https://www.w3schools.com/sql/func_count.asp)

---

**Desenvolvido durante o curso Java Spring Professional - DevSuperior** 🎓

