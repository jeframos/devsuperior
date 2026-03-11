# URI 2990 - Análise Complexa de Vendas

## 📌 Descrição

Desafio avançado que envolve análise multidimensional de dados de vendas com múltiplos JOINs, filtros complexos e funções analíticas.

**Dificuldade**: ⭐⭐⭐ Avançado

## 🎯 O Desafio

Escrever uma query SQL que:
1. Combina múltiplas tabelas com JOINs complexos
2. Aplica filtros e condições sofisticadas
3. Usa funções analíticas para análise dimensional
4. Calcula métricas consolidadas

## 🔍 Solução SQL

```sql
SELECT 
    c.name as customer_name,
    p.name as product_name,
    SUM(s.quantity) as total_quantity,
    SUM(s.quantity * s.price) as total_value,
    AVG(s.price) as avg_price,
    COUNT(DISTINCT s.id) as sales_count,
    ROW_NUMBER() OVER (PARTITION BY c.id ORDER BY SUM(s.quantity * s.price) DESC) as rank_by_customer
FROM customers c
INNER JOIN sales s ON c.id = s.customer_id
INNER JOIN products p ON s.product_id = p.id
WHERE s.sale_date >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR)
GROUP BY c.id, c.name, p.id, p.name
HAVING SUM(s.quantity) > 5
ORDER BY c.name, total_value DESC;
```

## 📝 Explicação

- **Window Function ROW_NUMBER()** - Rank dentro de cada cliente
- **PARTITION BY c.id** - Reinicia numeração por cliente
- **Múltiplos JOINs** - Conecta cliente, vendas e produtos
- **WHERE** - Filtra por período (último ano)
- **HAVING** - Filtra grupos com quantidade > 5
- **Agregações múltiplas** - SUM, AVG, COUNT

## 🧪 Dados de Teste

Resultado esperado exemplo:

| customer_name | product_name | total_quantity | total_value | avg_price | sales_count | rank_by_customer |
|---|---|---|---|---|---|---|
| João Silva | Notebook | 10 | 25000.00 | 2500.00 | 5 | 1 |
| João Silva | Monitor | 8 | 3200.00 | 400.00 | 4 | 2 |
| Maria Santos | Teclado | 12 | 1200.00 | 100.00 | 6 | 1 |

## 📚 Conceitos Avançados

### Window Functions
```sql
ROW_NUMBER() - Número sequencial
RANK() - Com empates
DENSE_RANK() - Sem gaps
LAG() / LEAD() - Acesso a linhas anteriores/posteriores
```

### CTEs (Common Table Expressions)
```sql
WITH customer_sales AS (
    SELECT c.id, SUM(s.quantity * s.price) as total
    FROM customers c
    INNER JOIN sales s ON c.id = s.customer_id
    GROUP BY c.id
)
SELECT * FROM customer_sales WHERE total > 1000;
```

## 🔗 Endpoints REST

```bash
GET /analytics/customer-product-analysis
GET /analytics/top-customers
GET /analytics/best-products
GET /analytics/sales-trends
```

## 🧪 Variações Avançadas

### Com CTE
```sql
WITH sales_by_customer AS (
    SELECT 
        c.id,
        c.name,
        COUNT(DISTINCT s.id) as sale_count,
        SUM(s.quantity * s.price) as total_spent
    FROM customers c
    INNER JOIN sales s ON c.id = s.customer_id
    GROUP BY c.id, c.name
)
SELECT * FROM sales_by_customer WHERE total_spent > 5000;
```

### Com CASE Statement
```sql
SELECT 
    c.name,
    COUNT(*) as total_sales,
    CASE 
        WHEN COUNT(*) > 100 THEN 'Premium'
        WHEN COUNT(*) > 50 THEN 'Gold'
        WHEN COUNT(*) > 10 THEN 'Silver'
        ELSE 'Bronze'
    END as customer_tier
FROM customers c
INNER JOIN sales s ON c.id = s.customer_id
GROUP BY c.id, c.name;
```

## ⚠️ Dicas de Performance

### 1. Índices Essenciais
```sql
CREATE INDEX idx_sales_customer_id ON sales(customer_id);
CREATE INDEX idx_sales_product_id ON sales(product_id);
CREATE INDEX idx_sales_date ON sales(sale_date);
```

### 2. Analisar Plano de Execução
```sql
EXPLAIN ANALYZE
-- sua query aqui
```

### 3. Evitar Loops N+1
```sql
-- ❌ EVITAR: Múltiplas queries
SELECT * FROM customers;
-- Para cada customer: SELECT FROM sales WHERE customer_id = ?

-- ✅ USAR: Uma query com JOIN
SELECT * FROM customers c
LEFT JOIN sales s ON c.id = s.customer_id;
```

## 🚀 Como Executar

```bash
cd uri2990
mvn spring-boot:run
```

## 📊 Imagem (Se Disponível)

Verifique a pasta do projeto por imagens que ilustrem o schema de dados (2990.png).

---

**Desenvolvido durante o curso Java Spring Professional - DevSuperior** 🎓

